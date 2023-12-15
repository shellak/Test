package com.shell.weatheralerts.utils.extensions

import android.content.res.Resources
import android.util.TypedValue

val Int.dpToPx: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)
