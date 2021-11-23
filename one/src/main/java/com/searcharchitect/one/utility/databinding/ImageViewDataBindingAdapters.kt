package com.searcharchitect.one.utility.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["profilePreview"])
fun setProfilePreview(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .circleCrop()
            .into(imageView)
    }
}

@BindingAdapter(value = ["profilePhoto"])
fun setProfilePhoto(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}