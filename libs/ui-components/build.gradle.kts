plugins {
    id("com.techlads.android.library")
    id("com.techlads.android.compose")
}

android {
    namespace = "com.techlads.uicomponents"
}

dependencies {
    implementation(libs.bundles.compose.tv)
}