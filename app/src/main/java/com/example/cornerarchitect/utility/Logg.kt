package com.example.cornerarchitect.utility

import android.util.Log
import android.util.Log.*
import androidx.databinding.ktx.BuildConfig

object Logg {

    private const val DEFAULT_MESSAGE = "Default log message."

    private val emptyMessage: () -> String = { DEFAULT_MESSAGE }

    fun v(tag: String? = null, message: () -> String = emptyMessage) = doForLevel(VERBOSE) {
        Log.v(getTag(tag), createNotEmptyMessage(message()))
    }

    fun v(tag: String? = null, message: () -> String = emptyMessage, exception: Exception) =
        doForLevel(VERBOSE) {
            Log.v(getTag(tag), createNotEmptyMessage(message()), exception)
        }

    fun d(tag: String? = null, message: () -> String = emptyMessage) = doForLevel(DEBUG) {
        Log.d(getTag(tag), createNotEmptyMessage(message()))
    }

    fun d(tag: String? = null, message: () -> String = emptyMessage, exception: Exception) =
        doForLevel(DEBUG) {
            Log.d(getTag(tag), createNotEmptyMessage(message()), exception)
        }

    fun i(tag: String? = null, message: () -> String = emptyMessage) = doForLevel(INFO) {
        Log.i(getTag(tag), createNotEmptyMessage(message()))
    }

    fun i(tag: String? = null, message: () -> String = emptyMessage, exception: Exception) =
        doForLevel(INFO) {
            Log.i(getTag(tag), createNotEmptyMessage(message()), exception)
        }

    fun w(tag: String? = null, message: () -> String = emptyMessage) = doForLevel(WARN) {
        Log.w(getTag(tag), createNotEmptyMessage(message()))
    }

    fun w(tag: String? = null, message: () -> String = emptyMessage, exception: Exception) =
        doForLevel(WARN) {
            Log.w(getTag(tag), createNotEmptyMessage(message()), exception)
        }

    fun e(tag: String? = null, message: () -> String = emptyMessage) = doForLevel(ERROR) {
        Log.e(getTag(tag), createNotEmptyMessage(message()))
    }

    fun e(tag: String? = null, message: () -> String = emptyMessage, exception: Exception) =
        doForLevel(ERROR) {
            Log.e(getTag(tag), createNotEmptyMessage(message()), exception)
        }

    fun wtf(tag: String? = null, message: () -> String = emptyMessage) = doForLevel(ASSERT) {
        Log.wtf(getTag(tag), createNotEmptyMessage(message()))
    }

    fun wtf(tag: String? = null, message: () -> String = emptyMessage, exception: Exception) =
        doForLevel(ASSERT) {
            Log.wtf(getTag(tag), createNotEmptyMessage(message()), exception)
        }

    private inline fun doForLevel(logLevel: Int, action: () -> Unit) {
        if (BuildConfig.DEBUG && logLevel >= VERBOSE) {
            action()
        }
    }

    private fun getTag(tag: String? = null): String {
        return if (tag.isNullOrEmpty()) createTag() else tag
    }

    private fun createNotEmptyMessage(message: String): String {
        return if (message.isEmpty()) DEFAULT_MESSAGE else "---> $message"
    }

    private fun createTag(): String {
        val callStackIndex = 4
        val callerElement = Throwable().stackTrace[callStackIndex]

        return "(${callerElement.fileName}:${callerElement.lineNumber})" +
                "- ${callerElement.methodName}"
    }

}

