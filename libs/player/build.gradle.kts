plugins {
    id("com.techlads.android.library")
}

android {
    namespace = "com.techlads.player"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    api(libs.timber)
}
