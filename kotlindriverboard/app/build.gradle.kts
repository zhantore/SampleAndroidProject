@Suppress("DSL_SCOPE_VIOLATION") // Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "kz.avtobys.driverboard"
    compileSdk = Integer.parseInt(libs.versions.compile.sdk.version.get())
    buildToolsVersion = libs.versions.build.tools.version.get()

    defaultConfig {
        applicationId = "kz.avtobys.driverboard"
        minSdk = Integer.parseInt(libs.versions.min.sdk.version.get())
        targetSdk = Integer.parseInt(libs.versions.target.sdk.version.get())
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    buildFeatures {
        compose = true
        android.buildFeatures.buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions.add("default")
    productFlavors {
        create("alpha") {
            dimension = "default"
            applicationId = "kz.avtobys.driverboard.alpha"
            buildConfigField("String", "app_name", "\"Avtobys Driver Board ALPHA\"")
            buildConfigField("String", "CLIENT_SECRET", "\"Basic Y2xpZW50OnNlY3JldA==\"")
            buildConfigField("String", "PASS_SECRET", "\"test\"")
            buildConfigField("String", "OAUTH_API_BASE_URL", "\"https://api.alpha.avtobys.kz\"")
            buildConfigField("String", "WEB_VIEW_URL", "\"https://driver.alpha.avtobys.kz\"")
        }
        create("beta") {
            dimension = "default"
            applicationId = "kz.avtobys.driverboard.beta"
            buildConfigField("String", "app_name", "\"Avtobys Driver Board BETA\"")
            buildConfigField("String", "CLIENT_SECRET", "\"Basic Y2xpZW50OnNlY3JldA==\"")
            buildConfigField("String", "PASS_SECRET", "\"test\"")
            buildConfigField("String", "OAUTH_API_BASE_URL", "\"https://api.beta.avtobys.kz\"")
            buildConfigField("String", "WEB_VIEW_URL", "\"https://driver.beta.avtobys.kz\"")
        }
        create("prod") {
            dimension = "default"
            applicationId = "kz.avtobys.driverboard"
            buildConfigField("String", "app_name", "\"Avtobys Driver Board\"")
            buildConfigField("String", "CLIENT_SECRET", "\"Basic YmIxeUFtWTpwaGhtMjFFVGpCb1FrbE5KMHExVnhn\"")
            buildConfigField("String", "PASS_SECRET", "\"pujAJEZ7CBV\"")
            buildConfigField("String", "OAUTH_API_BASE_URL", "\"https://api.avtobys.kz\"")
            buildConfigField("String", "WEB_VIEW_URL", "\"https://driver2.avtobys.kz/\"")
        }
    }
}

dependencies {

    // Jetpack
    implementation(libs.legacy.support)
    implementation(libs.lifecycle.live.data.ktx)
    implementation(libs.lifecycle.view.model.ktx)
    implementation(libs.lifecycle.runtime)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)

    //Kotlin
    implementation(libs.serialization)
    implementation(libs.coroutines.android)

    // androidXDependencies
    implementation(libs.app.compat)
    implementation(libs.material.design)
    implementation(libs.constraint.layout)
    implementation(libs.lifecycle.ext)
    implementation(libs.fragment.ktx)
    implementation(libs.core.ktx)
    implementation(libs.nav.ui)
    implementation(libs.nav.fragment)
    implementation(libs.view.pager2)
    implementation(libs.webkit)
    implementation(libs.security.crypto)

    // Chucker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.librarynoop)

    // Koin DI
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // NetworkDependencies
    implementation(libs.retrofit.core)
    implementation(libs.gson.converter)
    implementation(libs.gson)
    implementation(libs.ok.http)
    implementation(libs.ok.http.interceptor)

    // Maps
    implementation(libs.bundles.maps)

    // Projects
    implementation (project(":libraries:core"))

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}
