package com.corner.searcharchitect.model

import com.google.gson.annotations.SerializedName

data class FacebookAccessTokenRes(
    @SerializedName("access_token")
    var token: String,
    @SerializedName("token_type")
    var type: String,
)
