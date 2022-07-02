object Libraries {

    object Gradle {
        // Gradle Plugins
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val navigationSafeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationSafeArgsGradle}"
    }

    // Plugins
    object Plugins {
        const val androidApplication = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
    }

    object Core {
        const val androidCore = "androidx.core:core-ktx:${Versions.androidCoreVersion}"
        const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompactVersion}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    }

    object UI {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayoutVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiControllerVersion}"
        const val animatedBottomBar = "nl.joery.animatedbottombar:library:${Versions.animatedBottomBarVersion}"
        const val bubbleTabBar = "io.ak1:bubbletabbar:${Versions.bubbleTabBar}"
        const val smoothBottomBar = "com.github.ibrahimsn98:SmoothBottomBar:${Versions.smoothBottomBarVersion}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityVersion}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
        const val cardView = "androidx.cardview:cardview:${Versions.androidXSupportVersion}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidXSupportVersion}"
    }

    object Navigation {
        const val dynamicFeaturesNav = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    }

    object Test {
        const val jUnit = "junit:junit:"
        const val jUnitAndroidTest = "androidx.test.ext:junit:${Versions.junitVersion}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    }
}
