import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.bush.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bush.myapplication"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.01"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("git_release") {
            var keystoreProperties = Properties()
            var keystorePropsFile = file("keystore/keystore_config")

            if (keystorePropsFile.exists()) {
                keystorePropsFile.inputStream().use { keystoreProperties.load(it) }
                storeFile = file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            } else {
                storeFile = file("keystore/MTPL")
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("RELEASE_SIGN_KEY_ALIAS")
                keyPassword = System.getenv("RELEASE_SIGN_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("git_release")
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
    buildFeatures {
        viewBinding = true
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src\\main\\assets", "src\\main\\assets")
            }
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}