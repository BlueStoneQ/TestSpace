package com.example.nativetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import com.example.nativetest.ui.theme.NativeTestTheme
import android.webkit.WebView
import android.webkit.WebSettings
import android.util.Log

import com.example.nativetest.screen.MainScreen

class MainActivity : ComponentActivity() {
    private lateinit var webview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("[MainActivity]", "onCreate")
        webview = WebView(this)
        setContentView(webview)

        val webSettings: WebSettings = webview.settings
        webSettings.javaScriptEnabled = true

        setContent {
            MainScreen(
                onWebviewLoadRemoteUrl = ::loadRemoteHtml,
                onWebviewLoadResLocalUrl = ::loadResLocalHtml
            )
        }

//        loadRemoteHtml()
//        loadResLocalHtml()

//        setContent {
//            NativeTestTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Greeting(
////                        name = "Android",
////                        modifier = Modifier.padding(innerPadding)
////                    )
//
//                    Mwebview(
//                        name = "js bridge",
//                        modifier = Modifier.padding(innerPadding),
//                    )
//                }
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun loadRemoteHtml() {
        val remoteUrl = "https://www.baidu.com"
        setContentView(webview)
        webview.loadUrl(remoteUrl)
    }

    private fun loadResLocalHtml() {
        val localUrl = "file:///android_asset/local.html"
        webview.loadUrl(localUrl)
    }

    private fun loadDeviceLocalHtml() {}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (
    ){
        Text(
            text = "$name webview！！!",
            modifier = modifier
        )
        Text("123的为")
        Text("asdh")
    }
}

@Composable
fun Mwebview(name: String, modifier: Modifier = Modifier) {
    Text("$name+webview wrap")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NativeTestTheme {
//        Greeting("Android")
        Mwebview("123")
    }
}