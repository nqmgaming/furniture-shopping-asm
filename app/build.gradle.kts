import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.nqmgaming.furniture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nqmgaming.furniture"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Set value part

        val versionProps = Properties()
        versionProps.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "SUPABASE_ANON_KEY", versionProps["SUPABASE_ANON_KEY"].toString())
        buildConfigField("String", "SECRET", versionProps["SECRET"].toString())
        buildConfigField("String", "SUPABASE_URL", versionProps["SUPABASE_URL"].toString())
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Material 3 extended library
    implementation(libs.androidx.material.icons.extended)

    // Lottie
    implementation(libs.lottie.compose)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Superbase
    implementation(libs.postgrest.kt)
    implementation(libs.storage.kt)
    implementation(libs.gotrue.kt)

    // Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.utils)


    // Serialization
    implementation(libs.kotlinx.serialization.cbor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.json.okio)

    // https://coil-kt.github.io/coil/changelog/
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)

    // WorkManager
    implementation(libs.androidx.work.runtime)

    implementation(libs.slf4j.api)
    implementation(libs.slf4j.simple)

}