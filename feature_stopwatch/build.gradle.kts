plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(GradlePlugin.KOTLIN_ANDROID)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.DAGGER_HILT)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        /*consumerProguardFiles("consumer-rules.pro")*/
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":common_ui"))

    implementation(Dependencies.compose.material)
    implementation(Dependencies.android.ktx)
    implementation(Dependencies.compose.ui)
    implementation(Dependencies.compose.icons)
    implementation(Dependencies.android.material)
    implementation(Dependencies.compose.uiToolingPreview)
    androidTestImplementation(Dependencies.compose.uiTest)
    debugImplementation(Dependencies.compose.tooling)
    implementation(Dependencies.android.lifecycleRuntime)
    implementation(Dependencies.compose.activity)
    testImplementation(Dependencies.test.junit)
    androidTestImplementation(Dependencies.test.junitExt)
    androidTestImplementation(Dependencies.test.espressoCore)
    // Navigation
    /*implementation("com.google.accompanist:accompanist-navigation-animation:0.16.0")*/
    implementation(Dependencies.accompanist.animation)
    implementation(Dependencies.android.navigationRuntime)
    implementation(Dependencies.compose.navigation)
    // Hilt DI
    implementation(Dependencies.android.hilt.navigation)
    implementation(Dependencies.android.hilt.android)
    kapt(Dependencies.android.hilt.androidCompiler)
    kapt(Dependencies.android.hilt.compiler)
    // Coroutines
    implementation(Dependencies.coroutines.core)
    implementation(Dependencies.coroutines.android)
    implementation(Dependencies.android.lifecycleRuntime)
    implementation(Dependencies.android.lifecycleViewmodel)
    // DataStore
    implementation(Dependencies.android.dataStore)
    //ConstraintLayout
    implementation(Dependencies.compose.constraintLayout)
}