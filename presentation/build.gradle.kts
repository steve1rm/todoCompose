plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "me.androidbox.todocompose"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.version.androidx.compose.ui.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    // Android
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.android.material)
    implementation(libs.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.bundles.compose)

    // Accompanist Navigation Animation
    implementation(libs.accompanist.navigation.animation)

    // Dagger - Hilt
    kapt(libs.bundles.dagger.kapt)
    implementation(libs.hilt.android)
    implementation(libs.dagger)

    // Testing
    testImplementation(test.junit)
    androidTestImplementation(test.ext.junit)
    androidTestImplementation(test.espresso.core)
    androidTestImplementation(test.ui.test.junit4)
    debugImplementation(test.ui.tooling)
}
