package com.example.cornerarchitect.utility

sealed class Either<out L, out R> {

    data class Result<out R>(val result: R) : Either<Nothing, R>()
    data class Failure<out L>(val failure: L) : Either<L, Nothing>()

    fun either(failure: ((L) -> Unit)? = null, success: ((R) -> Unit)? = null): Any {
        return when (this) {
            is Failure -> failure?.invoke(this.failure) ?: Unit
            is Result -> success?.invoke(this.result) ?: Unit
        }
    }

}