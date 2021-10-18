package com.example.cornerarchitect.helper

import android.content.Context
import com.example.cornerarchitect.R
import javax.inject.Inject

interface ITextHelper {

    fun googleSheetsApiKey(): String

}

class TextHelper @Inject constructor(
    private val context: Context
) : ITextHelper {

    override fun googleSheetsApiKey(): String = context.getString(R.string.google_sheets_api_key)

}