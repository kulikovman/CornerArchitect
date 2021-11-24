package com.searcharchitect.common.retrofit.api

import com.searcharchitect.common.model.VkContainerRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IVkApi {

    @GET("/method/users.get")
    suspend fun getProfileInfo(
        @Query("user_ids") userIds: String,
        @Query("fields") fields: String,
        @Query("access_token") accessToken: String,
        @Query("v") apiVersion: String,
    ) : Response<VkContainerRes>

}