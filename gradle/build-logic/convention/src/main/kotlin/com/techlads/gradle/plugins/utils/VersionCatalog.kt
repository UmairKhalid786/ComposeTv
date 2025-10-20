package com.techlads.gradle.plugins.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.library(alias: String) = libs.findLibrary(alias).get()

internal fun Project.plugin(alias: String) = libs.findPlugin(alias).get().get().pluginId