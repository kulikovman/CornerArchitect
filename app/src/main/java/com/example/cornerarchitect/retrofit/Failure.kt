package com.example.cornerarchitect.retrofit

sealed class Failure {

    data class ConnectionError(
        val message: String = "Ошибка подключения",
        val description: String? = null
    ) : Failure()

    data class UnknownError(
        val message: String = "Неизвестная ошибка",
        val description: String? = null
    ) : Failure()

}