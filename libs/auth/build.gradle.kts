plugins {
    id("com.techlads.android.library")
}

android {
    namespace = "com.techlads.auth"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.core)
}