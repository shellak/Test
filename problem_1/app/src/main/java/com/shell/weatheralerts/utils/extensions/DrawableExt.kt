package com.shell.weatheralerts.utils.extensions

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable.*

fun Context.getCircularProgressDrawable(
    @ProgressDrawableSize size: Int = LARGE,
) = CircularProgressDrawable(this).apply {
    setStyle(size)
    start()
}
