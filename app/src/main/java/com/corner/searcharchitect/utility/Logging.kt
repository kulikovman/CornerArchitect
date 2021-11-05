package com.corner.searcharchitect.utility

import android.util.Log
import com.corner.searcharchitect.model.LogLevel

fun log(message: String, level: LogLevel = LogLevel.DEBUG) {
    val caller = Throwable().stackTrace[4] // 4 -> call stack index
    val tag = "(${caller.fileName}:${caller.lineNumber}) - ${caller.methodName}"

    when(level) {
        LogLevel.VERBOSE  -> Log.v(tag, "---> $message")
        LogLevel.DEBUG  -> Log.d(tag, "---> $message")
        LogLevel.INFO  -> Log.i(tag, "---> $message")
        LogLevel.WARN  -> Log.w(tag, "---> $message")
        LogLevel.ERROR  -> Log.e(tag, "---> $message")
        LogLevel.ASSERT  -> Log.wtf(tag, "---> $message")
    }
}