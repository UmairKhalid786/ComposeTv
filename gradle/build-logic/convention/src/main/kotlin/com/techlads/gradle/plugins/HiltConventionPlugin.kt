package com.techlads.gradle.plugins

import com.techlads.gradle.plugins.utils.implementation
import com.techlads.gradle.plugins.utils.kapt
import com.techlads.gradle.plugins.utils.library
import com.techlads.gradle.plugins.utils.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin("kotlin-kapt"))
                apply(plugin("hilt"))
            }

            dependencies {
                implementation(library("hilt-android"))
                kapt(library("hilt-compiler"))
            }
        }
    }
}