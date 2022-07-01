object Libraries {

    object Gradle {
        // Gradle Plugins
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val advancedBuildVersionGradle = "me.moallemi.gradle:advanced-build-version:${Versions.advancedBuildVersionGradle}"
        const val googleServicesGradle = "com.google.gms:google-services:${Versions.googleServicesGradle}"
        const val firebaseCrashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradle}"
        const val navigationSafeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationSafeArgsGradle}"
        const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltGradle}"
    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val kotlinAndroidExtensions = "kotlin-android-extensions"
        const val googleServices = "com.google.gms.google-services"
        const val advancedBuildVersion = "me.moallemi.advanced-build-version"
        const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
        const val daggerHilt = "dagger.hilt.android.plugin"
        const val crashlytics = "com.google.firebase.crashlytics"
    }

    object Core {

        // Android Dependencies
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
        const val kotlinReflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinReflectionVersion}"

        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidXSupportVersion}"
        const val androidCoreKtx = "androidx.core:core-ktx:${Versions.androidXCoreKtxVersion}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val lifeCycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val browser = "androidx.browser:browser:${Versions.browserVersion}"

        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
        const val workManager = "androidx.work:work-runtime-ktx:${Versions.work_manager_version}"
        const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"

        // Data store
        const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStorePreferencesVersion}"

        // Logging
        const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

        const val easyPermissions = "com.vmadalin:easypermissions-ktx:${Versions.easyPermission}"
    }

    object UI {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val coil = "io.coil-kt:coil:${Versions.coil_version}"

        const val circularImageView = "de.hdodenhof:circleimageview:${Versions.circularImageViewVersion}"
        const val pinView = "com.chaos.view:pinview:${Versions.pinViewVersion}"
        const val pagerIndicator = "com.romandanylyk:pageindicatorview:${Versions.pagerIndicator}"
        const val overScrollDecorator = "io.github.everythingme:overscroll-decor-android:${Versions.overScrollDecoratorVersion}"
        const val pdfViewer = "com.github.barteksc:android-pdf-viewer:${Versions.pdfViewerVersion}"
        const val pinEntryEditText = "com.alimuzaffar.lib:pinentryedittext:${Versions.pinEntryEditTextVersion}"
        const val customActivityOnCrash = "cat.ereza:customactivityoncrash:${Versions.customActivityOnCrashVersion}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity_version}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val cardView = "androidx.cardview:cardview:${Versions.androidXSupportVersion}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidXSupportVersion}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayoutVersion}"

        const val flowBindingAndroid = "io.github.reactivecircus.flowbinding:flowbinding-android:${Versions.flowbinding_version}"
        const val flowBindingMaterial = "io.github.reactivecircus.flowbinding:flowbinding-material:${Versions.flowbinding_version}"
    }

    object Compose {
        // Jetpack Compose
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
        const val composeAnimation = "androidx.compose.animation:animation:${Versions.composeVersion}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeUiToolingVersion}"
        const val composeLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeLifecycleVersion}"
        const val composeMaterialThemeAdapter = "com.google.android.material:compose-theme-adapter:${Versions.composeThemeAdapterVersion}"
    }

    object Navigation {
        const val dynamicFeaturesNav = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    }

    object Media {
        const val exoplayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer_version}"
        const val exoplayerUi = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer_version}"
    }

    object Firebase {
        const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBomVersion}"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics"
        const val firebaseAuthKtx = "com.google.firebase:firebase-auth-ktx"
        const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
        const val firebaseFirestoreKtx = "com.google.firebase:firebase-firestore-ktx"
        const val firebaseMessagingKtx = "com.google.firebase:firebase-messaging-ktx"
        const val firebaseStorageKtx = "com.google.firebase:firebase-storage-ktx"
        const val firebaseCoroutineSupport = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.firebaseCoroutinesVersion}"
    }

    object Dagger {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val hiltWorker = "androidx.hilt:hilt-work:${Versions.hiltWorkerVersion}"
        const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"
        const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHiltCompilerVersion}"
    }

    object Compilers {
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val junitAndroidTest = "androidx.test.ext:junit:"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
        const val workTesting = "androidx.work:work-testing:${Versions.workTestingVersion}"
        const val testCore = "androidx.test:core-ktx:${Versions.testCoreVersion}"
        const val supportAnnotations = "androidx.annotation:annotation:${Versions.androidXSupportVersion}"
        const val supportTestRunner = "androidx.test:runner:${Versions.androidXSupportTestVersion}"
        const val supportTestRules = "androidx.test:rules:${Versions.androidXSupportTestVersion}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.androidXEspressoVersion}"
        const val espressoContrib = "com.android.support.test.espresso:espresso-contrib:${Versions.androidXEspressoVersion}"
        const val navigationTest = "androidx.navigation:navigation-testing:${Versions.nav_version}"
        const val workManagerTest = "androidx.work:work-testing:${Versions.work_manager_version}"
    }
}
