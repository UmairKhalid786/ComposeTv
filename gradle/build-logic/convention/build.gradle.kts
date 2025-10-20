plugins {
    `kotlin-dsl`
}

dependencies {

    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

    testImplementation(libs.junit)
}


gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "com.techlads.android.compose"
            implementationClass = "com.techlads.gradle.plugins.ComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "com.techlads.android.application"
            implementationClass = "com.techlads.gradle.plugins.AppConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.techlads.android.library"
            implementationClass = "com.techlads.gradle.plugins.LibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "com.techlads.android.feature"
            implementationClass = "com.techlads.gradle.plugins.FeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "com.techlads.android.hilt"
            implementationClass = "com.techlads.gradle.plugins.HiltConventionPlugin"
        }
        register("androidJvm") {
            id = "com.techlads.android.jvm"
            implementationClass = "com.techlads.gradle.plugins.JvmConventionPlugin"
        }
    }
}