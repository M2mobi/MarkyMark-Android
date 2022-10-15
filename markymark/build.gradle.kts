plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    namespace = "com.m2mobi.markymark"
    compileSdk = Versions.TARGET_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = Versions.JVM
        targetCompatibility = Versions.JVM
    }

    kotlinOptions {
        jvmTarget = Versions.JVM.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_EXTENSION
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.5.1")

    implementation("androidx.compose.ui:ui:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}")
    implementation("androidx.compose.material3:material3:${Versions.MATERIAL}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.COMPOSE}")

    implementation("com.vladsch.flexmark:flexmark:${Versions.FLEXMARK}")
    implementation("com.vladsch.flexmark:flexmark-ext-autolink:${Versions.FLEXMARK}")
    implementation("com.vladsch.flexmark:flexmark-ext-gfm-strikethrough:${Versions.FLEXMARK}")
    implementation("com.vladsch.flexmark:flexmark-ext-gfm-tasklist:${Versions.FLEXMARK}")
    implementation("com.vladsch.flexmark:flexmark-ext-superscript:${Versions.FLEXMARK}")
    implementation("com.vladsch.flexmark:flexmark-ext-tables:${Versions.FLEXMARK}")

    implementation("io.coil-kt:coil:${Versions.COIL_KT}")
    implementation("io.coil-kt:coil-compose:${Versions.COIL_KT}")
}