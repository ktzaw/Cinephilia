buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        jcenter()
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    }

    dependencies {
        classpath(Libraries.Gradle.androidGradle)
        classpath(Libraries.Gradle.kotlinGradle)
        classpath(Libraries.Gradle.navigationSafeArgsGradle)
        classpath(Libraries.Gradle.kotlinSerializationGradle)
        classpath(Libraries.Gradle.hiltGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
        jcenter()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
