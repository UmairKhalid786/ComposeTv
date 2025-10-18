@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "ComposeTV"

include(
    // Apps
    ":app",

    // Libs
    ":libs:exoplayer",
    ":libs:player",
    ":libs:benchmark",
    ":libs:baselineprofile",
    ":libs:network",

    // Features
    ":features:config",
    ":features:content",
    ":features:login",
)
