plugins {
    id("com.techlads.android.library")
}

android {
    namespace = "com.techlads.exoplayer"
}

dependencies {
    implementation(projects.libs.player)

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.media3)
}
