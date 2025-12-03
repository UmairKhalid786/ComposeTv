package com.techlads.gradle.plugins.utils

import org.gradle.kotlin.dsl.DependencyHandlerScope

/**
 * Adds a dependency to the specified configuration.
 *
 * @param configurationName The name of the configuration (e.g., "implementation").
 * @param dependencyNotation The dependency notation.
 */
fun DependencyHandlerScope.addDependency(configurationName: String, dependencyNotation: Any) =
    dependencies.add(configurationName, dependencyNotation)

/**
 * Adds a dependency to the 'androidTestImplementation' configuration.
 */
fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) =
    addDependency("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the 'api' configuration.
 */
fun DependencyHandlerScope.api(dependencyNotation: Any) =
    addDependency("api", dependencyNotation)

/**
 * Adds a dependency to the 'debugImplementation' configuration.
 */
fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any) =
    addDependency("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the 'implementation' configuration.
 */
fun DependencyHandlerScope.implementation(dependencyNotation: Any) =
    addDependency("implementation", dependencyNotation)

/**
 * Adds a dependency to the 'kapt' configuration.
 */
fun DependencyHandlerScope.kapt(dependencyNotation: Any) =
    addDependency("kapt", dependencyNotation)
