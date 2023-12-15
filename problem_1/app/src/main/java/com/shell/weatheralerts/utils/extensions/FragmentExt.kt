package com.shell.weatheralerts.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.notify(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}
