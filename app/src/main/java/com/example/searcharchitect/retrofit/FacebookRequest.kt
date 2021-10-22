package com.example.searcharchitect.retrofit

import com.example.searcharchitect.utility.helper.ITextHelper
import com.example.searcharchitect.retrofit.api.IFacebookApi
import com.example.searcharchitect.utility.Either
import com.example.searcharchitect.utility.log
import javax.inject.Inject

interface IFacebookRequest {

    suspend fun getAccessToken(): Either<Failure, String>

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


    companion object {
        const val GRANT_TYPE = "client_credentials"
    }

}