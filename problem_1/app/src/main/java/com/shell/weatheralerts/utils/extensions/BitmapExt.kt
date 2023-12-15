package com.shell.weatheralerts.utils.extensions

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

fun Bitmap.getRoundedBitmapDrawable(resources: Resources, radius: Float) =
    RoundedBitmapDrawableFactory.create(resources, this).apply {
        cornerRadius = radius
    }