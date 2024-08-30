// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // The `alias` method is used when you are using version catalogs (introduced in Gradle 7.x)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Note: The following line should be used to declare dependencies to apply in the buildscript context.
        // This is useful for older plugins that don't support the plugins DSL
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        // Example for Hilt
    }
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
