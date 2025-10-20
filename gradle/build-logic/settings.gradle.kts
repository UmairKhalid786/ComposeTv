dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
":convention"
)

rootProject.name = "build-logic"
