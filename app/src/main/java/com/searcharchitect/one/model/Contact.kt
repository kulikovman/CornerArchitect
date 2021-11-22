package com.searcharchitect.one.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey
    val id: String,
    val login: String,
    val password: String,
    val surname: String,
    val name: String,
    val patronymic: String?,
    val region: String,
    val city: String,
    val specialization: String,
    val work: String?,
    val position: String?,
    val email: String?,
    val phone: String?,
    val telegram: String?,
    val instagram: String?,
    val facebook: String?,
    val vk: String?,
    val link: String?,
    val note: String?,
    var previewLink: String? = null,
    var photoLink: String? = null
)
