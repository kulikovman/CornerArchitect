package com.corner.searcharchitect.utility.helper

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

interface IToastHelper {

    fun showShort(message: String)
    fun showLong(message: String)

}

class ToastHelper @Inject constructor(
    private val context: Context
) : IToastHelper {

    override fun showShort(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLong(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}