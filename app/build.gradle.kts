import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Libraries.Plugins.androidApplication)
    id(Libraries.Plugins.kotlinAndroid)
    id(Libraries.Plugins.kotlinKapt)
    id(Libraries.Plugins.googleServices)
    id(Libraries.Plugins.daggerHilt)
    id(Libraries.Plugins.crashlytics)
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.appId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.compileSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Configs.instrumentationTestRunner

        val properties: Properties = Properties()
        properties.load(project.rootProject.file("key.properties").inputStream())

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    externalNativeBuild {
        cmake {
            path = File("$projectDir/CMakeLists.txt")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Libraries.Core.appCompact)
    implementation(Libraries.Core.androidCore)
    implementation(Libraries.Core.lifeCycleViewModelKtx)
    implementation(Libraries.Core.lifecycleRuntimeKtx)

    // Kotlin
    implementation(Libraries.Core.coroutinesCore)
    implementation(Libraries.Core.coroutinesAndroid)

    // UI
    implementation(Libraries.UI.activityKtx)
    implementation(Libraries.UI.fragmentKtx)
    implementation(Libraries.UI.constraintLayout)
    implementation(Libraries.UI.cardView)
    implementation(Libraries.UI.recyclerView)
    implementation(Libraries.UI.material)
    implementation(Libraries.UI.youtubePlayerView)

    implementation(Libraries.UI.glide)
    implementation(Libraries.UI.circleImageView)
    implementation(Libraries.UI.coil)

    // Navigation
    implementation(Libraries.Navigation.dynamicFeaturesNav)
    implementation(Libraries.Navigation.navigationFragmentKtx)
    implementation(Libraries.Navigation.navigationUiKtx)

    // Firebase
    implementation(platform(Libraries.Firebase.bom))
    implementation(Libraries.Firebase.firebaseAnalytics)
    implementation(Libraries.Firebase.firebaseAuthKtx)
    implementation(Libraries.Firebase.firebaseCrashlyticsKtx)
    implementation(Libraries.Firebase.firebaseFirestoreKtx)
    implementation(Libraries.Firebase.firebaseStorageKtx)
    implementation(Libraries.Firebase.firebaseCoroutineSupport)

    // Dependency Injection
    implementation(Libraries.Dagger.hiltAndroid)
    implementation(Libraries.Dagger.hiltWorker)
    kapt(Libraries.Dagger.androidxHiltCompiler)
    kapt(Libraries.Dagger.daggerHiltCompiler)

    // Data
    implementation(Libraries.Core.roomRuntime)
    implementation(Libraries.Core.roomKtx)
    kapt(Libraries.Compilers.roomCompiler)

    // Logs
    implementation(Libraries.Core.timber)

    // Data
    implementation(Libraries.Core.roomRuntime)
    implementation(Libraries.Core.roomKtx)
    kapt(Libraries.Compilers.roomCompiler)

    // Coroutine
    implementation(Libraries.Core.coroutinesCore)
    implementation(Libraries.Core.coroutinesAndroid)

    // Network
    implementation(Libraries.Network.retrofit2)
    implementation(Libraries.Network.retrofitGsonConverter)
    implementation(Libraries.Network.httpLoggingInterceptor)
    implementation(Libraries.Network.moshi)
    implementation(Libraries.Network.moshiKotlin)

    // Paging
    implementation(Libraries.Paging.pagingRuntime)
    testImplementation(Libraries.Paging.pagingCommon)

    // Tests
    testImplementation(Libraries.Test.jUnit)
    androidTestImplementation(Libraries.Test.jUnitAndroidTest)
    androidTestImplementation(Libraries.Test.espresso)
}
