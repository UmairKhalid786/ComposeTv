plugins {
    id("com.techlads.android.library")
    id("com.techlads.android.hilt")
}

android {
    namespace = "com.techlads.network"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3\"")
        buildConfigField("String", "TMDB_API_KEY", "\"\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.libs.auth)
    api(libs.bundles.ktor)
}