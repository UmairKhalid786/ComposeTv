enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

include(
    // Apps
    ":app",

    // Libs
    ":libs:auth",
    ":libs:benchmark",
    ":libs:baselineprofile",
    ":libs:content",
    ":libs:exoplayer",
    ":libs:network",
    ":libs:player",
    ":libs:ui-components",

    // Features
    ":features:config",
    ":features:login",
)

rootProject.name = "ComposeTV"
include(":libs:auth-imp")
