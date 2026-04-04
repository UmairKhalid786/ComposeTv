plugins {
    id("com.techlads.android.library")
    id("com.techlads.android.hilt")
}

val tmdbApiKey = providers.gradleProperty("tmdbApiKey")
    .orElse(providers.environmentVariable("TMDB_API_KEY"))
    .orElse("")
    .get()

android {
    namespace = "com.techlads.network"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3\"")
        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.libs.auth)
    api(libs.bundles.ktor)
}
