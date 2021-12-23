package com.searcharchitect.common.model

data class VkProfileInfo(
    var domain: String,
    var isPhoto: Boolean,
    var photoSquarePreview: String?,
    var photoSquareMax: String?,
    var photoOriginal: String?
)
