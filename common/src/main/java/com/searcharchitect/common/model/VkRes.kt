package com.searcharchitect.common.model

import kotlinx.serialization.Serializable

@Serializable
data class VkContainerRes(
    var response: List<VkProfileInfoRes>
)
