enableFeaturePreview("VERSION_CATALOGS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "Compose TV"
include(":app")
include(":player")
