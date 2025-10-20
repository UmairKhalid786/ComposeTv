package com.techlads.gradle.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class JvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        // ---- resolve JVM version lazily & safely
        val jvmVersionProvider = providers.gradleProperty("jvm.version")
            .map { it.toInt() }
            .map { it.takeIf { it in setOf(8, 11, 17, 21) } ?: 21 }
            .orElse(21)

        val javaVersionProvider = jvmVersionProvider.map(JavaVersion::toVersion)
        val jvmTargetProvider = jvmVersionProvider.map { it.toJvmTarget() }

        // ---- Java toolchain for Java modules
        pluginManager.withPlugin("java") {
            extensions.configure(JavaPluginExtension::class.java) {
                toolchain.languageVersion.set(JavaLanguageVersion.of(jvmVersionProvider.get()))
            }
        }

        // ---- Kotlin toolchain for any Kotlin plugin (android/jvm/mpp)
        // (Extension exists only when a Kotlin plugin is applied)
        extensions.findByType(KotlinBaseExtension::class.java)?.apply {
            jvmToolchain(jvmVersionProvider.get())
        }

        // ---- Kotlin compilerOptions.jvmTarget for all Kotlin compilations
        // (works for Kotlin/JVM tasks in Android & pure JVM modules)
        tasks.withType(KotlinJvmCompile::class.java).configureEach {
            compilerOptions {
                // Bind to provider so it’s config-cache friendly
                jvmTarget.set(jvmTargetProvider.get())
            }
        }

        tasks.withType<KotlinCompilationTask<KotlinJvmCompilerOptions>>().configureEach {
            compilerOptions {
                jvmTarget.set(jvmTargetProvider.map { it.ordinal.toJvmTarget() })
            }
        }

        // ---- Android (app/library/custom) modules in one place
        // CommonExtension covers application & library (and your custom plugins that apply them)
        extensions.findByType(CommonExtension::class.java)?.apply {
            compileOptions {
                sourceCompatibility = javaVersionProvider.get()
                targetCompatibility = javaVersionProvider.get()
            }
        }

        Unit
    }
}

// ---- helpers
private fun Int.toJvmTarget(): JvmTarget = when (this) {
    8  -> JvmTarget.JVM_1_8
    11 -> JvmTarget.JVM_11
    17 -> JvmTarget.JVM_17
    21 -> JvmTarget.JVM_21
    else -> JvmTarget.JVM_21
}