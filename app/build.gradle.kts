plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.5.0"
}

val composeVersion: String by project
val retrofitVersion: String by project
val moshiVersion: String by project
val koinVersion: String by project
val roomVersion: String by project
val moshiConverterVersion: String by project

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "cz.stepanzalis.spacexlifts"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions("environment")
    productFlavors {

        val prodBaseUrl = "\"https://api.spacexdata.com/v4\""
        val devBaseUrl = "\"https://api.spacexdata.com/v4\""

        create("dev") {
            buildConfigField("String", "API_BASE_URL", devBaseUrl)
        }
        create("prod") {
            buildConfigField("String", "API_BASE_URL", prodBaseUrl)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {

    // Core, AppCompat & Material
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.13.0")

    // Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.0-beta01")

    // Retrofit & Serialization
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$moshiConverterVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // DI
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    testImplementation("junit:junit:4.13.2")

    implementation("io.arrow-kt:arrow-optics:0.13.2")
    kapt("io.arrow-kt:arrow-meta:0.13.2")

}