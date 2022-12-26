import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Libraries.Plugins.androidApplication)
    id(Libraries.Plugins.kotlinAndroid)
    id(Libraries.Plugins.navigationSafeArgs)
    id(Libraries.Plugins.kotlinSerialization)
    id(Libraries.Plugins.kotlinKapt)
    id(Libraries.Plugins.daggerHilt)
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.appId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.compileSdkVersion
        versionCode = 1
        versionName = "1.0"
        renderscriptTargetApi = Configs.renderScriptTargetApi
        renderscriptSupportModeEnabled = Configs.renderScriptSupportModeEnabled

        testInstrumentationRunner = Configs.instrumentationTestRunner

        val properties: Properties = Properties()
        properties.load(project.rootProject.file("key.properties").inputStream())

        buildConfigField("String", "API_KEY", System.getenv("API_KEY") ?: properties.getProperty("apiKey"))
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "BASE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500/\"")
        buildConfigField("String", "BASE_IMAGE_ORIGINAL_SIZE_URL", "\"https://image.tmdb.org/t/p/original/\"")
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
        dataBinding = true
    }

    configurations {
    }
}

dependencies {

    // Android Core
    implementation(Libraries.Core.androidCore)
    implementation(Libraries.Core.appCompact)
    implementation(Libraries.Core.timber)
    implementation(Libraries.Core.kotlinSerialization)
    implementation(Libraries.Core.gson)
    implementation(Libraries.Core.kotlinReflection)
    implementation(Libraries.Core.workRuntime)
    implementation(Libraries.Core.lifecycleRuntimeKtx)
    implementation(Libraries.Core.kotlinxDateTime)
    implementation(Libraries.Core.lifeCycleViewModelKtx)
    implementation(Libraries.Core.roomRuntime)
    implementation(Libraries.Core.roomKtx)
    implementation(Libraries.Core.roomPaging)
    implementation(Libraries.Core.pagingKtx)
    implementation("com.google.android.material:material:1.7.0")
    kapt(Libraries.Core.roomCompiler)

    // UI
    implementation(Libraries.UI.material)
    implementation(Libraries.UI.constraintLayout)
    implementation(Libraries.UI.activityKtx)
    implementation(Libraries.UI.fragmentKtx)
    implementation(Libraries.UI.cardView)
    implementation(Libraries.UI.recyclerView)
    implementation(Libraries.UI.carouselRecyclerView)
    implementation(Libraries.UI.coil)
    implementation(Libraries.UI.overScrollDecorator)
    implementation(Libraries.UI.swipeRefreshLayout)
    implementation(Libraries.UI.paletteApi)
    implementation(Libraries.UI.glide)
    kapt(Libraries.UI.glideCompiler)

    // Navigation
    implementation(Libraries.Navigation.navigationUiKtx)
    implementation(Libraries.Navigation.navigationFragmentKtx)
    implementation(Libraries.Navigation.dynamicFeaturesNav)

    // Network
    implementation(Libraries.Network.retrofit2)
    implementation(Libraries.Network.okHttpLoggingInterceptor)
    implementation(Libraries.Network.gsonConverter)
    implementation(Libraries.Network.kotlinSerializationConverter)

    implementation(Libraries.Network.ktorAuth)
    implementation(Libraries.Network.ktorCore)
    implementation(Libraries.Network.ktorAndroid)
    implementation(Libraries.Network.ktorClientSerialization)
    implementation(Libraries.Network.ktorJson)
    implementation(Libraries.Network.ktorLogging)
    implementation(Libraries.Network.ktorLoggingJvm)
    implementation(Libraries.Network.ktorSerialization)
    implementation(Libraries.Network.ktorContentNegotiation)

    // Dependency Injection
    implementation(Libraries.Dagger.hiltAndroid)
    implementation(Libraries.Dagger.hiltWorker)
    kapt(Libraries.Dagger.androidxHiltCompiler)
    kapt(Libraries.Dagger.daggerHiltCompiler)

    // Test
    testImplementation(Libraries.Test.jUnit)
    androidTestImplementation(Libraries.Test.espresso)
    androidTestImplementation(Libraries.Test.jUnitAndroidTest)
    androidTestImplementation(Libraries.Test.navigationTest)
}
