package com.searcharchitect.one.retrofit.api

import com.searcharchitect.one.model.GoogleSheetRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IGoogleSheetsApi {

    @GET("/v4/spreadsheets/{spreadsheetId}/values/{range}")
    suspend fun getGoogleSheetData(
        @Path("spreadsheetId") sheetId: String?,
        @Path("range") range: String?,
        @Query("key") apiKey: String?
    ) : Response<GoogleSheetRes>

}