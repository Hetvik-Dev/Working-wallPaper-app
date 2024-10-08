plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.example.wallpaperapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wallpaperapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // hilt dagger
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.activity:activity:1.9.1")
    implementation("com.google.firebase:firebase-auth:23.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //navigation
    implementation("androidx.fragment:fragment-ktx:1.5.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

// Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.7")

// Glide
    implementation("com.github.bumptech.glide:glide:4.13.2")

    //material UI
    implementation("com.google.android.material:material:1.6.1")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.+")

    // allows to mock even variable is private
    testImplementation("org.mockito:mockito-inline:3.11.2")

    testImplementation("org.hamcrest:hamcrest:2.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
