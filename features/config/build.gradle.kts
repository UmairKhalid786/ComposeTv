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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.hilt.navigation.compose)
}