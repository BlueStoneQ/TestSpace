package com.example.nativetest.JSBridge

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class JsBridge(
    private val context: Context,
    private val webview: WebView
) {
    // call native fun
    @JavascriptInterface
    public fun getDeviceInfo(): String {
        return "msg from native"
    }

    @JavascriptInterface
    public fun showToast() {
        Log.d("[JsBridge]", "showToast")
        Toast
            .makeText(context, "js call native Toast", Toast.LENGTH_SHORT)
            .show()
    }
}