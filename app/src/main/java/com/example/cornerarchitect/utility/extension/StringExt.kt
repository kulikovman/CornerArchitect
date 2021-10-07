package com.example.cornerarchitect.utility.extension

import android.util.Patterns

fun String.containsLink(): Boolean {
    var result = false
    val parts = split("\\s+").toTypedArray()
    for (item in parts) {
        if (Patterns.WEB_URL.matcher(item).matches()) {
            result = true
            break
        }
    }
    return result
}