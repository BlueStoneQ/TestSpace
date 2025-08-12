package com.example.nativetest.jsBridge

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson

class JsBridge(
    private val context: Context,
    private val webview: WebView
) {
    val gson = Gson()

    public fun init(): JsBridge {
        inject()
        return this
    }

    public fun inject(): JsBridge {
        webview.addJavascriptInterface(this, "NativeBridge")
        return this
    }

    @JavascriptInterface
    public fun callNative(action: String, params: String, callbackId: String) {
        when (action) {
             "showToast" -> {
                 val toastParams = gson.fromJson(params, ToastParams::class.java)
                 showToast(context, toastParams.message)
                 callbackToJS(callbackId, "Toast exec success")
             }
            else -> callbackToJS(callbackId, "method not found: $action", true)
        }
    }

    private fun callbackToJS(callbackId: String, result: String, isError: Boolean = false) {
        val jsCode = ""
        webview.evaluateJavascript(jsCode) {}
    }

    data class ToastParams(val message: String)
}