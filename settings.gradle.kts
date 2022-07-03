
enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.40.2"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            version("version.kotlinx.coroutines", "_")
            version("version.kotlin", "_")
            version("version.google.dagger", "_")
            version("version.google.android.material", "_")
            version("version.google.accompat", "_")
            version("version.androidx.room", "_")
            version("version.androidx.lifecycle", "_")
            version("version.androidx.hilt", "_")
            version("version.androidx.datastore", "_")
            version("version.androidx.core", "_")
            version("version.androidx.compose.ui", "_")
            version("version.androidx.compose.material", "_")
            version("version.androidx.compose.compiler", "_")
            version("version.androidx.appcompat","_")
            version("version.androidx.activity",  "_")
            version("version.google.accompanist", "_")

            library("coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").versionRef("version.kotlinx.coroutines")
            library("kotlin", "org.jetbrains.kotlin", "kotlin-stdlib").versionRef("version.kotlin")

            library("dagger", "com.google.dagger", "dagger").versionRef("version.google.dagger")
            library("hilt-android", "com.google.dagger", "hilt-android").versionRef("version.androidx.hilt")
            library("hilt-android-compiler", "com.google.dagger", "hilt-android-compiler").versionRef("version.androidx.hilt")
            library("hilt-compiler", "androidx.hilt", "hilt-compiler").versionRef("version.androidx.hilt")
            library("dagger", "com.google.dagger", "dagger").versionRef("version.google.dagger")
            library("dagger-compiler", "com.google.dagger", "dagger-compiler").versionRef("version.google.dagger")
            library("dagger-android-processor", "com.google.dagger", "dagger-android-processor").versionRef("version.google.dagger")

            library("core-ktx", "androidx.core", "core-ktx").versionRef("version.androidx.core")
            library("appcompat", "androidx.appcompat", "appcompat").versionRef("version.androidx.appcompat")
            library("android.material", "com.google.android.material", "material").versionRef("version.google.android.material")
            library("ui", "androidx.compose.ui", "ui").versionRef("version.androidx.compose.ui")
            library("material","androidx.compose.material", "material").versionRef("version.androidx.compose.material")
            library("ui-tooling-preview","androidx.compose.ui", "ui-tooling-preview").versionRef("version.androidx.compose.ui")
            library("lifecycle-runtime-ktx","androidx.lifecycle", "lifecycle-runtime-ktx").versionRef("version.androidx.lifecycle")
            library("activity-compose", "androidx.activity", "activity-compose").versionRef("version.androidx.activity")

            library("accompanist-navigation-animation", "com.google.accompanist", "accompanist-navigation-animation").versionRef("version.google.accompanist")

            bundle("compose", listOf("ui", "material", "ui-tooling-preview", "activity-compose"))
            bundle("dagger-kapt", listOf("hilt-compiler", "hilt-android-compiler", "dagger-compiler", "dagger-android-processor"))
        }

        create("test") {
            version("version.androidx.test.ext.junit", "_")
            version("version.androidx.test.espresso", "_")
            version("version.junit.junit", "_")
            version("version.androidx.compose.ui", "_")

            library("junit","junit", "junit" ).versionRef("version.junit.junit")
            library("ext-junit", "androidx.test.ext", "junit").versionRef("version.androidx.test.ext.junit")
            library("espresso-core", "androidx.test.espresso", "espresso-core").versionRef("version.androidx.test.espresso")
            library("ui-test-junit4", "androidx.compose.ui", "ui-test-junit4").versionRef("version.androidx.compose.ui")
            library("ui-tooling", "androidx.compose.ui", "ui-tooling").versionRef("version.androidx.compose.ui")
        }

        create("plugin") {
            version("plugin.android", "_")
        }
    }
}

rootProject.name = "ToDoCompose"
include(":presentation", ":data", ":domain")
