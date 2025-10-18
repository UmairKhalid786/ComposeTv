plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.androidx.baselineprofile)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        namespace = "com.techlads.composetv"
        minSdk = libs.versions.minSdkTv.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidComposeCompiler.get()
    }

    kapt {
        correctErrorTypes = true
    }

    testOptions {
        managedDevices {
            localDevices {
                create("pixelTabletapi34") {
                    // Use device profiles you typically see in Android Studio.
                    device = "Pixel Tablet"
                    // Use only API levels 27 and higher.
                    apiLevel = 31
                    // To include Google services, use "google".
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }
}

dependencies {
    implementation(projects.libs.player)
    implementation(projects.libs.network)
    implementation(projects.libs.exoplayer)
    implementation(projects.features.config)
    implementation(projects.features.content)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose.bom)
    implementation(libs.bundles.compose.tv)
    implementation(libs.bundles.compose.accompanist)

    implementation(libs.kotlin.serialization)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.profileinstaller)
    "baselineProfile"(projects.libs.baselineprofile)

    implementation(libs.coil.core)
    implementation(libs.coil.compose)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.line.awesome.icons)

    androidTestImplementation(platform(libs.compose.bom))
}
