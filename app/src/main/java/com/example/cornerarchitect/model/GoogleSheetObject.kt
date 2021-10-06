package com.example.cornerarchitect.model

import com.example.cornerarchitect.utility.extension.convertToList
import com.example.cornerarchitect.utility.extension.getIfNotEmpty
import com.example.cornerarchitect.utility.log
import com.google.gson.annotations.SerializedName
import java.util.*

data class GoogleSheetObject(
    @SerializedName("range")
    var range: String,
    @SerializedName("majorDimension")
    var majorDimension: String,
    @SerializedName("values")
    var values: List<List<String>>
) {

    fun convertToDataVersion(): Int {
        return values[0][1].toInt()
    }

    fun convertToContactList(): List<Contact> {
        val contacts = mutableListOf<Contact>()

        values.forEach { sourceContactList ->
            try {
                sourceContactList.let { list ->
                    contacts.add(Contact(
                        id = UUID.randomUUID().toString(),
                        surname = list[0],
                        name = list[1],
                        city = list[2].convertToList(),
                        specialization = list[3].convertToList(),
                        work = list.getIfNotEmpty(4),
                        position = list.getIfNotEmpty(5),
                        email = list.getIfNotEmpty(6),
                        phone = list.getIfNotEmpty(7),
                        instagram = list.getIfNotEmpty(8),
                        facebook = list.getIfNotEmpty(9),
                        vk = list.getIfNotEmpty(10),
                    ))
                }
            } catch (e: Exception) {
                log("Exception in contact creating: $e")
            }
        }

        return contacts
    }

}