package com.shell.weatheralerts.utils.cache

import android.graphics.Bitmap
import javax.inject.Inject

class CacheImpl @Inject constructor() : Cache {

    // For better memory management, use LruCache and DiskCache or Glide library
    private val memoryCache = hashMapOf<String, Bitmap?>()

    override fun get(key: String) = memoryCache[key]

    override fun put(key: String, value: Bitmap) {
        memoryCache[key] = value
    }
}
