package com.example.searcharchitect.model

import com.google.gson.annotations.SerializedName

data class FacebookAccessTokenInfo(
    @SerializedName("access_token")
    var token: String,
    @SerializedName("token_type")
    var type: String,
)
