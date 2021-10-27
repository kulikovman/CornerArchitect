package com.example.searcharchitect.retrofit

import com.example.searcharchitect.model.VkProfileInfo
import com.example.searcharchitect.retrofit.api.IVkApi
import com.example.searcharchitect.utility.Either
import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.utility.log
import javax.inject.Inject

interface IVkRequest {

    suspend fun getPhotoPreviewList(userIds: List<String>): Either<Failure, List<VkProfileInfo>>

    suspend fun getProfileInfo(userId: String): Either<Failure, String>

}

class VkRequest @Inject constructor(
    private val text: ITextHelper,
    private val vkApi: IVkApi
) : IVkRequest {

    override suspend fun getPhotoPreviewList(userIds: List<String>): Either<Failure, List<VkProfileInfo>> {
        return try {
            val request = vkApi.getUserInfo(
                userIds = userIds.joinToString(separator = ","),
                fields = "domain,deactivated,has_photo,photo_100,photo_max_orig",
                accessToken = text.vkServiceKey(),
                apiVersion = API_VERSION
            )

            if (request.isSuccessful) {
                request.body()?.response?.map { it.convertToVkProfileInfo() }
                    ?.filter { it.isPhoto }?.let { profileInfoList ->
                        Either.Result(profileInfoList)
                    } ?: Either.Failure(Failure.UnknownError())
            } else Either.Failure(Failure.UnknownError())
        } catch (e: Exception) {
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    override suspend fun getProfileInfo(userId: String): Either<Failure, String> {
        return try {
            val request = vkApi.getUserInfo(
                userIds = userId,
                fields = "bdate,photo_max",
                accessToken = text.vkServiceKey(),
                apiVersion = API_VERSION
            )

            if (request.isSuccessful) {
                log("Request: ${request.body().toString()}")
                request.body()?.response?.getOrNull(0)?.photo_max_orig?.let { photo50 ->
                    Either.Result(photo50)
                } ?: Either.Failure(Failure.UnknownError())
            } else Either.Failure(Failure.UnknownError())
        } catch (e: Exception) {
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    companion object {

        const val API_VERSION = "5.131"

    }

}