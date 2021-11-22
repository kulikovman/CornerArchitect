package com.searcharchitect.one.utility.extension

import kotlinx.coroutines.*

inline fun <reified T> T?.orIfNull(input: () -> T): T {
    return this ?: input()
}

fun <T> debounce(
    delayMillis: Long = 300L,
    scope: CoroutineScope = MainScope(),
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}