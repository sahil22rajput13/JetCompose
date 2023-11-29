package com.example.jetcompose.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Context.Toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    val adjustedDuration = (duration * density).toInt()

    Toast.makeText(context, message, adjustedDuration).show()
}
