package com.searcharchitect.one.model

import com.google.gson.annotations.SerializedName

data class VkContainerRes(
    @SerializedName("response")
    var response: List<VkProfileInfoRes>
)
