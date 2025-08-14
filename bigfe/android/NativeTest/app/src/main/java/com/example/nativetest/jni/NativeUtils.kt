package com.example.nativetest.jni

import android.util.Log
import java.io.DataInput

object NativeUtils {
    private const val TAG = "NativeUtils"

    init {
        try {
            System.loadLibrary("native-lib")
            Log.d(TAG, "load native-lib success")
        } catch (err: UnsatisfiedLinkError) {
            Log.d(TAG, "load native-lib err: ${err.message}")
        }
    }

    external fun calculateSum(a: Int, b: Int): Int
}