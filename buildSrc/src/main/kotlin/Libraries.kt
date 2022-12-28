object Libraries {

    object Gradle {
        // Gradle Plugins
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val navigationSafeArgsGradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationSafeArgsGradle}"
        const val kotlinSerializationGradle = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}"
        const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltGradle}"
    }

    // Plugins
    object Plugins {
        const val androidApplication = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinSerialization = "kotlinx-serialization"
        const val kotlinKapt = "kotlin-kapt"
        const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
        const val daggerHilt = "dagger.hilt.android.plugin"
    }

    object Core {
        const val androidCore = "androidx.core:core-ktx:${Versions.androidCoreVersion}"
        const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompactVersion}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
        const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationVersion}"
        const val kotlinReflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlinReflectionVersion}"
        const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
        const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.workRuntimeVersion}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val lifeCycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"

        const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"

        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomPaging = "androidx.room:room-paging:${Versions.roomVersion}"

        const val pagingKtx = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
    }

    object UI {
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayoutVersion}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUiControllerVersion}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityVersion}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
        const val cardView = "androidx.cardview:cardview:${Versions.androidXSupportVersion}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidXSupportVersion}"
        const val carouselRecyclerView = "com.github.sparrow007:carouselrecyclerview:${Versions.carouselRecyclerViewVersion}"
        const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
        const val overScrollDecorator = "io.github.everythingme:overscroll-decor-android:${Versions.overScrollDecoratorVersion}"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayoutVersion}"
        const val paletteApi = "androidx.palette:palette:${Versions.paletteApiVersion}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object Network {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptorVersion}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"
        const val kotlinSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverterVersion}"

        const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
        const val ktorJson = "io.ktor:ktor-client-json:${Versions.ktorVersion}"
        const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktorVersion}"
        const val ktorLoggingJvm = "io.ktor:ktor-client-logging-jvm:${Versions.ktorVersion}"
        const val ktorSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"
        const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
        const val ktorAuth = "io.ktor:ktor-client-auth:${Versions.ktorVersion}"
        const val ktorClientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktorVersion }"
        const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktorVersion}"
    }

    object Dagger {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val hiltWorker = "androidx.hilt:hilt-work:${Versions.hiltWorkerVersion}"
        const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"
        const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHiltCompilerVersion}"
    }

    object Navigation {
        const val dynamicFeaturesNav = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val jUnitAndroidTest = "androidx.test.ext:junit:${Versions.junitAndroidTestVersion}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
        const val navigationTest = "androidx.navigation:navigation-testing:${Versions.nav_version}"
    }
}
