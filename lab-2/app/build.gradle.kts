plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.kotlinlabs"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.kotlinlabs"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "greeting"
    productFlavors {
        create("withName") {
            dimension = "greeting"
            applicationIdSuffix = ".withname"
            buildConfigField("boolean", "SHOW_NAME", "true")
        }
        create("withoutName") {
            dimension = "greeting"
            applicationIdSuffix = ".withoutname"
            buildConfigField("boolean", "SHOW_NAME", "false")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2026.09.00"))
    implementation("androidx.activity:activity-compose:1.13.0")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation(platform("androidx.compose:compose-bom:2026.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test:runner:1.7.0")
    // Compose UI Test's transitive Espresso version cannot initialize on API 37.
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}
