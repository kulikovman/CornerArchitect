package com.example.searcharchitect.utility.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["preview"])
fun setPreview(imageView: ImageView, link: String?) {
    link?.let {
        Glide.with(imageView.context)
            .load(link)
            .circleCrop()
            .into(imageView)
    }
}