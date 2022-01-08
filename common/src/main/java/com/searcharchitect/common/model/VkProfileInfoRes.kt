package com.searcharchitect.common.model

import com.searcharchitect.common.utility.extension.toBoolean
import kotlinx.serialization.Serializable

// По типам возвращаемых полей читать здесь - https://vk.com/dev/objects

@Serializable
data class VkProfileInfoRes(
    var id: Int,
    var first_name: String,
    var last_name: String,
    var can_access_closed: Boolean,
    var is_closed: Boolean,

    var domain: String?, // Короткий адрес страницы
    var has_photo: Int?, // Есть ли фото профиля (1 - да, 0 - нет)
    var photo_100: String?, // Квадратное фото 100х100
    var photo_max: String?, // Квадратное фото максимального размера
    var photo_max_orig: String?, // Фото максимального размера
) {

    fun convertToVkProfileInfo(): VkProfileInfo {
        return VkProfileInfo(
            domain = domain.orEmpty(),
            isPhoto = has_photo.toBoolean(),
            photoSquarePreview = photo_100,
            photoSquareMax = photo_max,
            photoOriginal = photo_max_orig
        )
    }

}
