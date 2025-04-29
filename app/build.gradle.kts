
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // - это
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.example.cheerup"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.example.cheerup"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
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
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.androidx.animation.core.lint)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.2.1") // Для аутентификации
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.2.1")

    // Обязательный HTTP-клиент
    implementation("io.ktor:ktor-client-okhttp:2.3.7")

    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.2.1") // Для PostgREST API
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.2.1") // Для Realtime
    implementation("io.github.jan-tennert.supabase:storage-kt:2.2.1") // Для Storage
    implementation("io.github.jan-tennert.supabase:functions-kt:2.2.1") // Для Edge Functions

    implementation("io.ktor:ktor-client-okhttp:2.3.7") // HTTP-клиент
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0") // Сериализация

    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.2.1")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.2.1")
    implementation("io.ktor:ktor-client-okhttp:2.3.7")


    // - это
    // Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Навигация
    implementation("androidx.navigation:navigation-compose:2.8.6")

    // Supabase

    implementation(platform(libs.bom))
    implementation(libs.postgrest.kt)
    implementation(libs.auth.kt)
    implementation(libs.realtime.kt)
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.1.0")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.1.0")
    // Ktor
    implementation(libs.ktor.client.android)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.coil.compose)

    implementation ("androidx.compose.material:material-icons-extended:1.6.7")
}
