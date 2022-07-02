import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Libraries.Plugins.androidApplication)
    id(Libraries.Plugins.kotlinAndroid)
    id(Libraries.Plugins.navigationSafeArgs)
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

        buildConfigField("String", "API_KEY", properties.getProperty("apiKey"))
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
}

dependencies {

    // Android Core
    implementation(Libraries.Core.androidCore)
    implementation(Libraries.Core.appCompact)
    implementation(Libraries.Core.timber)

    // UI
    implementation(Libraries.UI.material)
    implementation(Libraries.UI.constraintLayout)
    implementation(Libraries.UI.animatedBottomBar)
    implementation(Libraries.UI.bubbleTabBar)
    implementation(Libraries.UI.smoothBottomBar)
    implementation(Libraries.UI.activityKtx)
    implementation(Libraries.UI.fragmentKtx)
    implementation(Libraries.UI.cardView)
    implementation(Libraries.UI.recyclerView)

    // Navigation
    implementation(Libraries.Navigation.navigationUiKtx)
    implementation(Libraries.Navigation.navigationFragmentKtx)

    // Test
    testImplementation(Libraries.Test.jUnit)
    androidTestImplementation(Libraries.Test.espresso)
    androidTestImplementation(Libraries.Test.jUnitAndroidTest)
}
