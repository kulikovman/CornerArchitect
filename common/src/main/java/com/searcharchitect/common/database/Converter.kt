package com.searcharchitect.common.database

import androidx.room.TypeConverter
import com.searcharchitect.common.utility.Constant.DELIMITER

class Converter {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        var text = ""
        list.forEach { part ->
            text += if (text.isEmpty()) part else "$DELIMITER$part"
        }

        return text
    }

    @TypeConverter
    fun fromStringToList(text: String): List<String> {
        return text.split(DELIMITER)
    }

}