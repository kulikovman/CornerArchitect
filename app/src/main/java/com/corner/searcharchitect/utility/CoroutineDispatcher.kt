package com.corner.searcharchitect.utility

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface ICoroutineDispatcher {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}

class CoroutineDispatcher @Inject constructor(): ICoroutineDispatcher {

    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO

}