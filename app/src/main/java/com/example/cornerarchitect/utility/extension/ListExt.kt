package com.example.cornerarchitect.utility.extension

fun List<String>.getIfNotEmpty(index: Int): String? {
    return getOrNull(index)?.takeIf { it.isNotEmpty() }
}