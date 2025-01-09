plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "me.gigajet.haicottien"
    compileSdk = 35

    defaultConfig {
        applicationId = "me.gigajet.haicottien"
        minSdk = 21
        targetSdk = 35
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packaging {
        /**
         * Execution failed for task ':app:mergeDebugJavaResource'.
         * > A failure occurred while executing com.android.build.gradle.internal.tasks.MergeJavaResWorkAction
         *    > 2 files found with path 'META-INF/native-image/native-image.properties' from inputs:
         *       - org.mongodb:mongodb-driver-core:5.2.1/mongodb-driver-core-5.2.1.jar
         *       - org.mongodb:bson:5.2.1/bson-5.2.1.jar
         *      Adding a packaging block may help, please refer to
         *      https://developer.android.com/reference/tools/gradle-api/com/android/build/api/dsl/Packaging
         *      for more information
         */
        resources.excludes.add("/META-INF/native-image/**")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /* Jetpack Compose: https://developer.android.com/develop/ui/compose/setup#kotlin_1 */
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    // Material Design 3
    implementation(libs.androidx.material3)
    // Android Studio Preview support
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    // UI Tests
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}