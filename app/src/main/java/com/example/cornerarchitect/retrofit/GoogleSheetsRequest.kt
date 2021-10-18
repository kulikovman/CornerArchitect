package com.example.cornerarchitect.retrofit

import com.example.cornerarchitect.helper.ITextHelper
import com.example.cornerarchitect.model.Contact
import com.example.cornerarchitect.retrofit.api.IGoogleSheetsApi
import com.example.cornerarchitect.utility.Either
import javax.inject.Inject

interface IGoogleSheetsRequest {

    suspend fun getDataVersion(): Either<Failure, Int>
    suspend fun getContactList(): Either<Failure, List<Contact>>

}

class GoogleSheetsRequest @Inject constructor(
    private val text: ITextHelper,
    private val googleSheetsApi: IGoogleSheetsApi
) : IGoogleSheetsRequest {

    override suspend fun getDataVersion(): Either<Failure, Int> {
        return try {
            val request = googleSheetsApi.getGoogleSheetData(
                sheetId = SHEETS_ID,
                range = DATA_SHEET,
                apiKey = text.googleSheetsApiKey()
            )

            if (request.isSuccessful) {
                request.body()?.convertToDataVersion()?.let { version ->
                    Either.Result(version)
                } ?: Either.Failure(Failure.UnknownError())
            } else Either.Failure(Failure.UnknownError())
        } catch (e: Exception) {
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    override suspend fun getContactList(): Either<Failure, List<Contact>> {
        return try {
            val request = googleSheetsApi.getGoogleSheetData(
                sheetId = SHEETS_ID,
                range = CONTACTS_SHEET,
                apiKey = text.googleSheetsApiKey()
            )

            if (request.isSuccessful) {
                request.body()?.convertToContactList()?.let { contacts ->
                    Either.Result(contacts)
                } ?: Either.Failure(Failure.UnknownError())
            } else Either.Failure(Failure.UnknownError())
        } catch (e: Exception) {
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    companion object {
        const val SHEETS_ID = "16a83JzuwJkla6hpKpK2w1OpL6ALDvQHW8omroLQEi4I"
        const val DATA_SHEET = "Data!A1:B1"
        const val CONTACTS_SHEET = "Contacts"
    }

}