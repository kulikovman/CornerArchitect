package com.corner.searcharchitect.utility.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.corner.searcharchitect.utility.extension.setVisibleOrGone
import com.corner.searcharchitect.utility.extension.setVisibleOrInvisible

@BindingAdapter("goneIfEmpty")
fun goneIfEmpty(view: View, text: String?) {
    view.setVisibleOrGone(text?.isNotEmpty() ?: false)
}

@BindingAdapter("goneIfEmptyList")
fun goneIfEmptyList(view: View, list: List<Any>?) {
    view.setVisibleOrGone(list?.isNotEmpty() ?: false)
}

@BindingAdapter("goneIfFalse")
fun goneIfFalse(view: View, isVisible: Boolean?) {
    view.setVisibleOrGone(isVisible ?: false)
}

@BindingAdapter("goneIfTrue")
fun goneIfTrue(view: View, isVisible: Boolean?) {
    view.setVisibleOrGone(!(isVisible ?: true))
}

@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, any: Any?) {
    view.setVisibleOrGone(any != null)
}

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, any: Any?) {
    view.setVisibleOrGone(any == null)
}

@BindingAdapter("invisibleIfNull")
fun invisibleIfNull(view: View, any: Any?) {
    view.setVisibleOrInvisible(any != null)
}