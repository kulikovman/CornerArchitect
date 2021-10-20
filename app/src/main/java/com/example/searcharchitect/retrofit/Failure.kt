package com.example.searcharchitect.retrofit

sealed class Failure {

    data class ConnectionError(val description: String? = null) : Failure()
    data class UnknownError(val description: String? = null) : Failure()

}