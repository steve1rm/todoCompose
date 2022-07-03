buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${plugin.versions.plugin.android.get()}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.version.kotlin.get()}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${libs.versions.version.androidx.hilt.get()}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
