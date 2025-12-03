plugins {
    id("com.techlads.android.application")
    id("com.techlads.android.compose")
    id("com.techlads.android.hilt")
    alias(libs.plugins.androidx.baselineprofile)
    alias(libs.plugins.kotlin.serialization)
}

android {
    defaultConfig {
        namespace = "com.techlads.composetv"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
}

dependencies {
    implementation(projects.libs.authImp)
    implementation(projects.libs.player)
    implementation(projects.libs.content)
    implementation(projects.libs.network)
    implementation(projects.libs.exoplayer)
    implementation(projects.libs.uiComponents)

    implementation(projects.features.login)
    implementation(projects.features.config)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.tv)
    implementation(libs.bundles.compose.accompanist)
    implementation(libs.compose.material.iconsExtended)

    implementation(libs.kotlin.serialization)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.profileinstaller)
    "baselineProfile"(projects.libs.baselineprofile)

    implementation(libs.coil.core)
    implementation(libs.coil.compose)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.line.awesome.icons)
}
