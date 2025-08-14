package com.example.nativetest

import android.nfc.Tag
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.nativetest.ui.theme.NativeTestTheme
import android.webkit.WebView
import android.webkit.WebSettings
import android.util.Log

import com.example.nativetest.screen.MainScreen
import com.example.nativetest.jsBridge.JsBridge
import com.example.nativetest.jni.NativeUtils

const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private lateinit var webview: WebView
    private lateinit var jsBridge: JsBridge

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d(TAG, "onCreate")

        initWebview()
        initJsBridge()
        initContent()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initWebview() {
        // enable debug, access the chrome://inspect/#devices in Chrome
        WebView.setWebContentsDebuggingEnabled(true)

        webview = WebView(this)
        setContentView(webview)

        val webSettings: WebSettings = webview.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
    }

    private fun initJsBridge() {
        jsBridge = JsBridge(this, webview)
        jsBridge.init()
    }

    private fun initContent() {
        setContent {
            NativeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        onWebviewLoadRemoteUrl = ::loadRemoteHtml,
                        onWebviewLoadResLocalUrl = ::loadResLocalHtml,
                        onJNICallSum = ::onJNICallSum,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun loadRemoteHtml() {
        val remoteUrl = "https://www.baidu.com"
        setContentView(webview)
        webview.loadUrl(remoteUrl)
    }

    private fun loadResLocalHtml() {
        val localUrl = "file:///android_asset/local.html"
        setContentView(webview)
        webview.loadUrl(localUrl)
    }

    private fun loadDeviceLocalHtml() {
        // TODO:
    }

    private fun onJNICallSum() {
        val res = NativeUtils.calculateSum(1, 2)
        Log.d(TAG, "onJNICallSum $res")
    }
}