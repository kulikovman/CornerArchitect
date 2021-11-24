package com.searcharchitect.common.model

import com.google.gson.annotations.SerializedName
import com.searcharchitect.common.utility.extension.toBoolean

// По типам возвращаемых полей читать здесь - https://vk.com/dev/objects

data class VkProfileInfoRes(
    @SerializedName("id")
    var id: Int,
    @SerializedName("first_name")
    var first_name: String,
    @SerializedName("last_name")
    var last_name: String,
    @SerializedName("can_access_closed")
    var can_access_closed: Boolean,
    @SerializedName("is_closed")
    var is_closed: Boolean,

    @SerializedName("domain")
    var domain: String?, // Короткий адрес страницы
    @SerializedName("has_photo")
    var has_photo: Int?, // Есть ли фото профиля (1 - да, 0 - нет)
    @SerializedName("photo_100")
    var photo_100: String?, // Квадратное фото 100х100
    @SerializedName("photo_max")
    var photo_max: String? // Квадратное фото максимального размера
) {

    fun convertToVkProfileInfo(): VkProfileInfo {
        return VkProfileInfo(
            domain = domain.orEmpty(),
            isPhoto = has_photo.toBoolean(),
            previewLink = photo_100,
            photoLink = photo_max
        )
    }

}
