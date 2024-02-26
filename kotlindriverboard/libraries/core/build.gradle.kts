@Suppress("DSL_SCOPE_VIOLATION") // Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.jetbrains.kotlin)
    alias(libs.plugins.android.library)
}

android {
    namespace = "kz.avtobys.core"
    compileSdk = Integer.parseInt(libs.versions.compile.sdk.version.get())

    defaultConfig {
        minSdk = Integer.parseInt(libs.versions.min.sdk.version.get())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // androidXDependencies
    implementation(libs.app.compat)
    implementation(libs.core.ktx)
    implementation(libs.material.design)

    // NetworkDependencies
    implementation(libs.retrofit.core)
    implementation(libs.gson.converter)
    implementation(libs.gson)

    implementation(libs.bundles.compose)

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}