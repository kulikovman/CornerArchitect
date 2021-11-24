package com.searcharchitect.common.model

import com.google.gson.annotations.SerializedName
import com.searcharchitect.common.utility.extension.getIfExist
import com.searcharchitect.common.utility.extension.getWithTrim
import com.searcharchitect.common.utility.log
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
                                login = list.getWithTrim(0),
                                password = list.getWithTrim(1),
                                surname = list.getWithTrim(2),
                                name = list.getWithTrim(3),
                                patronymic = list.getIfExist(4),
                                region = list.getWithTrim(5),
                                city = list.getWithTrim(6),
                                specialization = list.getWithTrim(7),
                                work = list.getIfExist(8),
                                position = list.getIfExist(9),
                                email = list.getIfExist(10),
                                phone = list.getIfExist(11),
                                telegram = list.getIfExist(12),
                                instagram = list.getIfExist(13),
                                facebook = list.getIfExist(14),
                                vk = list.getIfExist(15),
                                link = list.getIfExist(16),
                                note = list.getIfExist(17)
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