package com.example.cornerarchitect.model

import com.example.cornerarchitect.utility.extension.getAsList
import com.example.cornerarchitect.utility.extension.getIfExist
import com.example.cornerarchitect.utility.extension.getWithTrim
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
                    contacts.add(
                        Contact(
                            id = UUID.randomUUID().toString(),
                            surname = list.getWithTrim(0),
                            name = list.getWithTrim(1),
                            city = list.getAsList(2),
                            specialization = list.getAsList(3),
                            work = list.getIfExist(4),
                            position = list.getIfExist(5),
                            email = list.getWithTrim(6),
                            phone = list.getWithTrim(7),
                            instagram = list.getIfExist(8),
                            facebook = list.getIfExist(9),
                            vk = list.getIfExist(10),
                            link = list.getIfExist(11),
                            note = list.getIfExist(12)
                        )
                    )
                }
            } catch (e: Exception) {
                log("Exception in contact creating: $e")
            }
        }

        return contacts
    }

}