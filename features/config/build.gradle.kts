plugins {
    id("com.techlads.android.feature")
    id("com.techlads.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.techlads.config"
}

dependencies {
    implementation(projects.libs.network)
}