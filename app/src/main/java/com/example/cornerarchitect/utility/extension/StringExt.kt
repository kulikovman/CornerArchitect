package com.example.cornerarchitect.utility.extension

import com.example.cornerarchitect.utility.Constant

fun String?.convertToList(): List<String> {
    return if (!this.isNullOrEmpty()) {
        split(Constant.DELIMITER).map { it.trim() }
    } else emptyList()
}