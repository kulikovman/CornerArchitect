package com.searcharchitect.common.model

import com.google.gson.annotations.SerializedName

data class VkContainerRes(
    @SerializedName("response")
    var response: List<VkProfileInfoRes>
)
