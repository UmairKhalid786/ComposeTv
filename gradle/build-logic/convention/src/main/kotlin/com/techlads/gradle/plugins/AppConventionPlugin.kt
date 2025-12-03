package com.techlads.gradle.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.techlads.gradle.plugins.utils.Versions
import com.techlads.gradle.plugins.utils.configureAndroid
import com.techlads.gradle.plugins.utils.configureKotlin
import com.techlads.gradle.plugins.utils.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin("android-application"))
                apply(plugin("kotlin-android"))
                apply("com.techlads.android.jvm")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)

                defaultConfig.targetSdk = Versions.TARGET_SDK
            }

            configureKotlin()
        }
    }
}