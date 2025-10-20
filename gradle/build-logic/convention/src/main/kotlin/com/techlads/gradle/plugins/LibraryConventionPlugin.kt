package com.techlads.gradle.plugins

import com.android.build.gradle.LibraryExtension
import com.techlads.gradle.plugins.utils.Versions
import com.techlads.gradle.plugins.utils.configureAndroid
import com.techlads.gradle.plugins.utils.configureKotlin
import com.techlads.gradle.plugins.utils.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin("android-library"))
                apply(plugin("kotlin-android"))
                apply("com.techlads.android.jvm")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)

                defaultConfig.targetSdk = Versions.TARGET_SDK
            }

            configureKotlin()
        }
    }
}