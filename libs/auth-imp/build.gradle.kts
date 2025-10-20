plugins {
    id("com.techlads.android.library")
    id("com.techlads.android.hilt")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.techlads.authimp"
}

dependencies {
    api(projects.libs.auth)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.core)
}