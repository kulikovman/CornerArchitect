package com.example.searcharchitect.retrofit.api

import com.example.searcharchitect.model.FacebookAccessTokenRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IFacebookApi {

    @GET("/oauth/access_token")
    suspend fun getAccessToken(
        @Query("client_id") appId: String,
        @Query("client_secret") appSecret: String,
        @Query("grant_type") grantType: String,
    ) : Response<FacebookAccessTokenRes>

    @GET("/{userId}")
    suspend fun getProfileInfo(
        @Path("userId") userId: String,
        @Query("fields") fields: String,
        @Query("access_token") token: String
    ) : Response<Unit>

}