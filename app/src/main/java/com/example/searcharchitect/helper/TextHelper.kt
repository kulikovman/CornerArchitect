package com.example.searcharchitect.helper

import android.content.Context
import com.example.searcharchitect.R
import javax.inject.Inject

interface ITextHelper {

    fun googleSheetsApiKey(): String

}

class TextHelper @Inject constructor(
    private val context: Context
) : ITextHelper {

    override fun googleSheetsApiKey(): String = context.getString(R.string.google_sheets_api_key)

}