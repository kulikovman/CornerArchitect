package com.example.searcharchitect.model

import com.example.searcharchitect.utility.extension.getIfExist
import com.example.searcharchitect.utility.extension.getWithTrim
import com.example.searcharchitect.utility.log
import com.google.gson.annotations.SerializedName
import java.util.*

data class GoogleSheetRes(
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

        values.forEachIndexed { index, contactInfoList ->
            try {
                if (index > 0) { // Titles in first element
                    contactInfoList.let { list ->
                        contacts.add(
                            Contact(
                                id = UUID.randomUUID().toString(),
                                surname = list.getWithTrim(0),
                                name = list.getWithTrim(1),
                                patronymic = list.getIfExist(2),
                                region = list.getWithTrim(3),
                                city = list.getWithTrim(4),
                                specialization = list.getWithTrim(5),
                                work = list.getIfExist(6),
                                position = list.getIfExist(7),
                                email = list.getIfExist(8),
                                phone = list.getIfExist(9),
                                telegram = list.getIfExist(10),
                                instagram = list.getIfExist(11),
                                facebook = list.getIfExist(12),
                                vk = list.getIfExist(13),
                                link = list.getIfExist(14),
                                note = list.getIfExist(15)
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                log("Exception in contact creating: $e")
            }
        }

        return contacts
    }

}