buildscript {
    repositories {
        google()
        mavenCentral()
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
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
