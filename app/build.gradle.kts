plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.rudra.titangymmanager"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.rudra.titangymmanager"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {




    // -----------------------------
    // Lifecycle & ViewModel (MVVM)
    // -----------------------------
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    // -----------------------------
    // Room Database (Offline Storage)
    // -----------------------------
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // -----------------------------
    // Hilt (Dependency Injection)
    // -----------------------------
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // -----------------------------
    // Kotlin
    // -----------------------------
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")

    // -----------------------------
    // Coroutines
    // -----------------------------
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // -----------------------------
    // Navigation
    // -----------------------------
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // -----------------------------
    // Coil (Image Loading for member photos)
    // -----------------------------
    implementation("io.coil-kt:coil-compose:2.7.0")

    // -----------------------------
    // DataStore (if you want app settings)
    // -----------------------------
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // -----------------------------
    // Local Notifications
    // -----------------------------
    implementation("androidx.work:work-runtime-ktx:2.9.1")

    // -----------------------------
    // Splash Screen
    // -----------------------------
    implementation("androidx.core:core-splashscreen:1.0.1")

    // -----------------------------
    // Chart Library (Optional)
    // -----------------------------
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // -----------------------------
    // Permission Handling (optional)
    // -----------------------------
    implementation("com.google.accompanist:accompanist-permissions:0.36.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}