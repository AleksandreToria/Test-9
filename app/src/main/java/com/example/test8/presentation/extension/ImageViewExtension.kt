package com.example.test8.presentation.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.test8.R


fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this);
}