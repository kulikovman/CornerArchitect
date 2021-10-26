package com.example.searcharchitect.retrofit

import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.retrofit.api.IFacebookApi
import com.example.searcharchitect.utility.Either
import com.example.searcharchitect.utility.log
import javax.inject.Inject

interface IFacebookRequest {

    suspend fun getAccessToken(): Either<Failure, String>
    suspend fun getProfileInfo(userId: String, token: String)

}

class FacebookRequest @Inject constructor(
    private val text: ITextHelper,
    private val facebookApi: IFacebookApi
) : IFacebookRequest {

    override suspend fun getAccessToken(): Either<Failure, String> {
        return try {
            val request = facebookApi.getAccessToken(
                appId = text.facebookAppId(),
                appSecret = text.facebookAppSecret(),
                grantType = GRANT_TYPE
            )

            if (request.isSuccessful) {
                request.body()?.token?.let { token ->
                    Either.Result(token)
                } ?: Either.Failure(Failure.UnknownError())
            } else Either.Failure(Failure.UnknownError())
        } catch (e: Exception) {
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    override suspend fun getProfileInfo(userId: String, token: String) {
        try {
            val request = facebookApi.getProfileInfo(
                userId = userId,
                fields = PROFILE_DATA,
                token = token
            )

            if (request.isSuccessful) {
                log(request.body().toString())
                /*request.body()?.token?.let { token ->
                    Either.Result(token)
                } ?: Either.Failure(Failure.UnknownError())*/
            } //else Either.Failure(Failure.UnknownError())
        } catch (e: Exception) {
            //Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    companion object {
        const val GRANT_TYPE = "client_credentials"
        const val PROFILE_DATA = "picture"
    }

}