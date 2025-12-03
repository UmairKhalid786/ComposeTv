package com.techlads.gradle.plugins.utils

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroidCompose() {
    android {
        buildFeatures {
            compose = true
        }
    }

    dependencies {
        implementation(platform(library("compose-bom")))
        androidTestImplementation(platform(library("compose-bom")))
    }
}

private fun Project.android(action: CommonExtension<*, *, *, *, *, *>.() -> Unit) = extensions.configure(CommonExtension::class, action)