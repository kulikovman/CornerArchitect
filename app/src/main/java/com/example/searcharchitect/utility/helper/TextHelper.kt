package com.example.searcharchitect.utility.helper

import android.content.Context
import com.example.searcharchitect.R
import javax.inject.Inject

interface ITextHelper {

    fun googleSheetsApiKey(): String

    fun facebookAppSecret(): String
    fun facebookAppId(): String

    fun vkProtectedKey(): String
    fun vkServiceKey(): String

    fun connectionError(): String
    fun unknownError(): String

}

class TextHelper @Inject constructor(
    private val context: Context
) : ITextHelper {

    override fun googleSheetsApiKey(): String = context.getString(R.string.google_sheets_api_key)


    override fun facebookAppSecret(): String = context.getString(R.string.facebook_client_token)

    override fun facebookAppId(): String = context.getString(R.string.facebook_app_id)


    override fun vkProtectedKey(): String = context.getString(R.string.vk_protected_key)

    override fun vkServiceKey(): String = context.getString(R.string.vk_service_key)


    override fun connectionError(): String = context.getString(R.string.connection_error)

    override fun unknownError(): String = context.getString(R.string.unknown_error)

}