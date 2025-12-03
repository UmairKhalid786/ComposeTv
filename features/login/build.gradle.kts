plugins {
    id("com.techlads.android.feature")
    id("com.techlads.android.compose")
    id("com.techlads.android.hilt")
}

android {
    namespace = "com.techlads.login"
}

dependencies {
    implementation(projects.libs.content)
    implementation(projects.libs.uiComponents)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.qrcode)

    implementation(libs.coil.core)
    implementation(libs.coil.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose.bom)
    implementation(libs.bundles.compose.tv)

    implementation(libs.androidx.hilt.navigation.compose)
}