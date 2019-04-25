package com.test.homeautomation.utils

import android.widget.Toast
import com.test.homeautomation.App

object MessageHelper {
    fun showToast(message: String) {
        Toast.makeText(
            App.context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}