buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.android.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}