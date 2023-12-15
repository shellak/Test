package com.shell.weatheralerts.utils.cache

import android.graphics.Bitmap


interface Cache {
    fun get(key: String): Bitmap?
    fun put(key: String, value: Bitmap)
}
