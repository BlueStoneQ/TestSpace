package com.example.nativetest.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MainScreen(
    onWebviewLoadRemoteUrl: () -> Unit,
    onWebviewLoadResLocalUrl: () -> Unit,
    onJNICallSum: () -> Unit,
    modifier: Modifier = Modifier
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

        Button(
            onClick = {
                onJNICallSum()
            }
        ) {
            Text("jni:执行NativeUtils.calculateSum")
        }
    }
}