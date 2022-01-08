package com.searcharchitect.common.utility.extension

import com.searcharchitect.common.utility.Constant

fun List<String>.getAsList(index: Int): List<String> {
    return get(index).trim().takeIf { it.isNotEmpty() }
        ?.split(Constant.DELIMITER)?.map { it.trim() }
        ?: throw IllegalArgumentException("Field must not be empty!")
}

fun List<String>.getIfExist(index: Int): String? {
    return getOrNull(index)?.trim()?.takeIf { it.isNotEmpty() }
}

fun List<String>.getWithTrim(index: Int): String {
    return get(index).trim().takeIf { it.isNotEmpty() }
        ?: throw IllegalArgumentException("Field must not be empty!")
}