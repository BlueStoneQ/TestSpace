package com.example.nativetest.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun MainScreen(
    onWebviewLoadRemoteUrl: () -> Unit,
    onWebviewLoadResLocalUrl: () -> Unit
) {
    Column {
        Button(
            onClick = {
                onWebviewLoadRemoteUrl()
            }
        ) {
            Text("webview加载远程url")
        }

        Button(
            onClick = {
                onWebviewLoadResLocalUrl()
            }
        ) {
            Text("webview加载项目本地url")
        }
    }
}