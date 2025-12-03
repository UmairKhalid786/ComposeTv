package com.techlads.gradle.plugins

import com.techlads.gradle.plugins.utils.configureAndroidCompose
import com.techlads.gradle.plugins.utils.debugImplementation
import com.techlads.gradle.plugins.utils.implementation
import com.techlads.gradle.plugins.utils.library
import com.techlads.gradle.plugins.utils.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) =
        with(target) {
            with(pluginManager) {
                apply(plugin("compose-compiler"))
            }

            pluginManager.withPlugin("com.android.base") {
                configureAndroidCompose()
            }

            dependencies {
                debugImplementation(library("androidx-compose-ui-tooling"))
                implementation(library("androidx-compose-ui-tooling-preview"))
            }
        }
}