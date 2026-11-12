plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "cl.colmena.assistant"
    compileSdk = 35
    defaultConfig {
        applicationId = "cl.colmena.assistant"
        minSdk = 26
        targetSdk = 35
        versionCode = 2
        versionName = "0.2.0"
    }
}
