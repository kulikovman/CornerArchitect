package com.searcharchitect.one.utility.databinding

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.searcharchitect.common.utility.extension.containsLink

@BindingAdapter(value = ["htmlText"])
fun formatHtmlText(textView: TextView, htmlText: String?) {
    htmlText?.let { text ->
        if (text.containsLink()) {
            textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).trim()
            textView.movementMethod = LinkMovementMethod.getInstance()
        } else {
            textView.text = text
        }
    }
}