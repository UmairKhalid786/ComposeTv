plugins {
    id("com.techlads.android.library")
    id("com.techlads.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.techlads.content"
}

dependencies {
    api(projects.libs.network)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.hilt.navigation.compose)
}