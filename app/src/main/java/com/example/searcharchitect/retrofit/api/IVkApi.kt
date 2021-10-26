package com.example.searcharchitect.retrofit.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IVkApi {

    @GET("/method/users.get")
    suspend fun getUserInfo(
        @Query("user_ids") userIds: String,
        @Query("fields") fields: String,
        @Query("access_token") accessToken: String,
        @Query("v") apiVersion: String,
    ) : Response<Unit>

}