package com.searcharchitect.common.retrofit

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.searcharchitect.common.model.VkProfileInfo
import com.searcharchitect.common.retrofit.api.IVkApi
import com.searcharchitect.common.utility.Either
import com.searcharchitect.common.utility.helper.ITextHelper
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
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    companion object {

        const val API_VERSION = "5.131"

    }

}