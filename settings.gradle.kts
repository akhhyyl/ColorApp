pluginManagement {
    repositories {
        google() // Google's Maven repository
        mavenCentral() // Maven Central repository
        gradlePluginPortal() // Gradle Plugin Portal
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Disallow project-specific repositories
    repositories {
        google() // Google's Maven repository
        mavenCentral() // Maven Central repository
    }
}

rootProject.name = "ColorApp"
include(":app")
 