#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "[Native-lib.cpp]"
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__))

extern "C" JNIEXPORT jint JNICALL
Java_com_example_nativetest_jni_NativeUtils_calculateSum(
        JNIEnv* env,
        jobject,
        jint a,
        jint b) {
    return a + b;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativetest_jni_NativeUtils_processString(
        JNIEnv* env,
        jobject,
        jstring input
        ) {
    // javaString 2 c string
    const char* cInput = env->GetStringUTFChars(input, nullptr);
    if (cInput == nullptr) {
        return nullptr;
    }

    std::string processed = "[after JNI processed]" + std::string(cInput);

    LOGD("this log from jni-cpp:Native-lib:processString");

    env->ReleaseStringUTFChars(input, cInput);

    return env->NewStringUTF(processed.c_str());
}

extern "C" JNIEXPORT jfloat JNICALL
Java_com_example_nativetest_jni_NativeUtils_calculateAverage(
        JNIEnv* env,
        jobject,
        jintArray intArray
        ) {
    jsize length = env->GetArrayLength(intArray);
    if (length <= 0) {
        return 0.0f;
    }

    jint* elements = env->GetIntArrayElements(intArray, nullptr);
    if (elements == nullptr) {
        return 0.0f;
    }

    jint sum = 0;
    for (int i = 0; i < length; i++) {
        sum += elements[i];
    }

    env->ReleaseIntArrayElements(intArray, elements, 0);

    return static_cast<jfloat>(sum) / length;
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_nativetest_jni_NativeUtils_processUser(
        JNIEnv* env,
        jobject,
        jobject user
        ) {
    jclass userClass = env->GetObjectClass(user);
    if (userClass == nullptr) {
        return;
    }

    jfieldID ageField = env->GetFieldID(userClass, "age", "I");
    if (ageField == nullptr) {
        return;
    }

    jint currentAge = env->GetIntField(user, ageField);

    env->SetIntField(user, ageField, currentAge + 10);

    jfieldID nameField = env->GetFieldID(userClass, "name", "Ljava/lang/String;");
    if (nameField == nullptr) {
        return;
    }

    jstring newName = env->NewStringUTF("JNI_Processed_Name");

    env->SetObjectField(user, nameField, newName);

    env->DeleteLocalRef(userClass);
    env->DeleteLocalRef(newName);
}

