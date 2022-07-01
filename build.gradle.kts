buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libraries.Gradle.androidGradle)
        classpath(Libraries.Gradle.kotlinGradle)
        classpath(Libraries.Gradle.googleServicesGradle)
        classpath(Libraries.Gradle.firebaseCrashlyticsGradle)
        classpath(Libraries.Gradle.hiltGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
