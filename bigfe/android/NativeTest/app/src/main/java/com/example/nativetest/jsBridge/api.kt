package com.example.nativetest.jsBridge

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, text, duration).show()
}