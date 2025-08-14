#include <jni.h>
#include <android/log.h>

#define LOG_TAG "JNI_DEMO"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jint JNICALL
Java_com_example_nativetest_jni_NativeUtils_calculateSum(
        JNIEnv* env,
        jobject,
        jint a,
        jint b) {
    return a + b;
}
