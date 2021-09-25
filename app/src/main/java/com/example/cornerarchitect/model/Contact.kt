package com.example.cornerarchitect.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val surname: String,
    val city: List<String>,
    val specialization: List<String>,
    val work: List<String>,
    val email: List<String>,
    val phone: List<String>,
    val instagram: String? = null,
    val facebook: String? = null,
    val vk: String? = null
)
