buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        classpath(Libraries.Gradle.androidGradle)
        classpath(Libraries.Gradle.kotlinGradle)
        classpath(Libraries.Gradle.navigationSafeArgsGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
