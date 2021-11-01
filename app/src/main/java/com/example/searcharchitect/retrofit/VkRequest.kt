package com.example.searcharchitect.retrofit

import com.example.searcharchitect.model.VkProfileInfo
import com.example.searcharchitect.retrofit.api.IVkApi
import com.example.searcharchitect.utility.Either
import com.example.searcharchitect.utility.helper.ITextHelper
import javax.inject.Inject

interface IVkRequest {

    suspend fun getPhotoPreviewList(domains: List<String>): Either<Failure, List<VkProfileInfo>>

}

class VkRequest @Inject constructor(
    private val text: ITextHelper,
    private val vkApi: IVkApi
) : IVkRequest {

    override suspend fun getPhotoPreviewList(domains: List<String>): Either<Failure, List<VkProfileInfo>> {
        return try {
            val request = vkApi.getProfileInfo(
                userIds = domains.joinToString(separator = ","),
                fields = "domain,has_photo,photo_100,photo_max", // Описание полей в data классе
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

    companion object {

        const val API_VERSION = "5.131"

    }

}