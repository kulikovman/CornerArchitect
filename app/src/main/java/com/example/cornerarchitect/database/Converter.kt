package com.example.cornerarchitect.database

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        var text = ""
        list.forEach { part ->
            text += "$DELIMITER$part"
        }

        return text
    }

    @TypeConverter
    fun fromStringToList(text: String): List<String> {
        return text.split(DELIMITER)
    }


    companion object {
        const val DELIMITER = "/"
    }

}