@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(mapOf("path" to ":player")))
    implementation(project(mapOf("path" to ":exoplayer")))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose.bom)
    implementation(libs.bundles.compose.tv)
    implementation(libs.bundles.compose.accompanist)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.qrcode)
    implementation(libs.line.awesome.icons)

    androidTestImplementation(platform(libs.compose.bom))
}
