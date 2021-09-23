package com.example.cornerarchitect.model

data class Contact(
    val name: String,
    val surname: String,
    val city: List<String>,
    val specialization: List<String>,
    val work: List<String>,
    val email: List<String>,
    val phone: List<String>,
    val instagram: String,
    val facebook: String,
    val vk: String
)
