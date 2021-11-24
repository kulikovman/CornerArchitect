package com.searcharchitect.common.retrofit

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.retrofit.api.IGoogleSheetsApi
import com.searcharchitect.common.utility.Either
import com.searcharchitect.common.utility.helper.ITextHelper
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
            } else {
                FirebaseCrashlytics.getInstance().log("Request body: ${request.body().toString()}")
                Either.Failure(Failure.UnknownError())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().log(e.toString())
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
            } else {
                FirebaseCrashlytics.getInstance().log("Request body: ${request.body().toString()}")
                Either.Failure(Failure.UnknownError())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().log(e.toString())
            Either.Failure(Failure.ConnectionError(e.toString()))
        }
    }

    companion object {
        const val SHEETS_ID = "16a83JzuwJkla6hpKpK2w1OpL6ALDvQHW8omroLQEi4I"
        const val DATA_SHEET = "Data!A1:B1"
        const val CONTACTS_SHEET = "Contacts"
    }

}