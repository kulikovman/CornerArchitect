package com.example.cornerarchitect.utility.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View = this.currentFocus
        ?: this.findViewById<View>(android.R.id.content)?.rootView
        ?: View(this)

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboardWithClearFocus() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View = this.currentFocus
        ?: this.findViewById<View>(android.R.id.content)?.rootView
        ?: View(this)

    imm.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}