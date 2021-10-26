package com.example.searcharchitect.retrofit

import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.retrofit.api.IFacebookApi
import com.example.searcharchitect.retrofit.api.IVkApi
import com.example.searcharchitect.utility.Either
import com.example.searcharchitect.utility.log
import javax.inject.Inject

interface IVkRequest {

    suspend fun getProfileInfo(userId: String)

}

class VkRequest @Inject constructor(
    private val text: ITextHelper,
    private val vkApi: IVkApi
) : IVkRequest {

    override suspend fun getProfileInfo(userId: String) {
        try {
            val request = vkApi.getUserInfo(
                userIds = userId,
                fields = "bdate",
                accessToken = text.vkServiceKey(),
                apiVersion = API_VERSION
            )

            if (request.isSuccessful) {
                log("Get response from vk...")
                log(request.body().toString())
            }
        } catch (e: Exception) {
            //Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    companion object {

        const val API_VERSION = "5.131"

    }

}