package com.example.cornerarchitect.model

import com.example.cornerarchitect.utility.log
import com.google.gson.annotations.SerializedName

data class GoogleSheetObject(
    @SerializedName("range")
    var range: String,
    @SerializedName("majorDimension")
    var majorDimension: String,
    @SerializedName("values")
    var values: Array<Array<String>>
) {

    fun convertToDataVersion(): Int {
        return values[0][1].toInt()
    }

    fun convertToContactList(): List<Contact> {
        val contacts = mutableListOf<Contact>()

        values.forEach { contactArray ->
            try {
                contactArray.toList().let { list ->
                    contacts.add(Contact(
                        surname = list[0],
                        name = list[1],
                        city = list[2].fromStringToList(),
                        specialization = list[3].fromStringToList(),
                        work = list.getOrNull(4),
                        position = list.getOrNull(5),
                        email = list.getOrNull(6),
                        phone = list.getOrNull(7),
                        instagram = list.getOrNull(8),
                        facebook = list.getOrNull(9),
                        vk = list.getOrNull(10),
                    ))
                }
            } catch (e: Exception) {
                log("Exception in contact creating: $e")
            }
        }

        return contacts
    }

}

private fun String?.fromStringToList(): List<String> {
    return if (this != null) {
        split("/").map { it.trim() }
    } else emptyList()
}