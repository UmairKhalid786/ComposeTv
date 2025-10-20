package com.techlads.gradle.plugins.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlin() {
    dependencies {
        implementation(platform(library("kotlin-bom")))
    }
}
