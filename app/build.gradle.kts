plugins {
    id "com.android.application"
    id "kotlin-android"
    id "kotlin-parcelize"
    id "com.google.devtools.ksp"
}

android {
    compileSdkVersion 33
    buildToolsVersion "33.0.2"
    ndkVersion "25.2.9519653"

    splits {
        abi {
            enable = true
            reset()
            include "arm64-v8a", "x86_64", "armeabi-v7a", "x86"
            universalApk = true
        }
    }

    signingConfigs {
        sign {
            storeFile file(projectDir.path + "/keystore/androidkey.jks")
            storePassword "000000"
            keyAlias "key0"
            keyPassword "000000"
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    defaultConfig {
        applicationId "org.moedog.ehviewer"
        minSdkVersion 28
        targetSdkVersion 33
        versionCode 172809
        versionName "1.7.28.7"
        resourceConfigurations += ["zh", "zh-rCN", "zh-rHK", "zh-rTW", "ja"]
    }

    externalNativeBuild {
        cmake {
            path "src/main/cpp/CMakeLists.txt"
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_19
        targetCompatibility JavaVersion.VERSION_19
    }

    kotlinOptions {
        jvmTarget = "19"
        freeCompilerArgs += [
            // https://kotlinlang.org/docs/compiler-reference.html#progressive
            "-progressive"
        ]
    }

    lint {
        abortOnError true
        checkReleaseBuilds false
        disable "MissingTranslation"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/**"
            excludes += "/kotlin/**"
            excludes += "**.txt"
            excludes += "**.bin"
        }
    }

    dependenciesInfo.includeInApk false

    buildTypes {
        release {
            minifyEnabled true
            shrinkResources true
            proguardFiles "proguard-rules.pro"
            signingConfig signingConfigs.sign
        }
        debug {
            applicationIdSuffix ".debug"
            signingConfig signingConfigs.debug
        }
    }

    buildFeatures {
        buildConfig true
    }

    namespace "com.hippo.ehviewer"
}

dependencies {
    implementation "androidx.activity:activity-ktx:1.6.1"
    implementation "androidx.biometric:biometric-ktx:1.2.0-alpha05"
    implementation "androidx.browser:browser:1.5.0"
    implementation "androidx.collection:collection-ktx:1.3.0-alpha02"
    implementation "androidx.core:core-ktx:1.10.0-alpha01"
    implementation "androidx.coordinatorlayout:coordinatorlayout:1.2.0"
    implementation "androidx.fragment:fragment-ktx:1.6.0-alpha04"
    implementation "androidx.lifecycle:lifecycle-process:2.5.1"
    implementation "androidx.paging:paging-runtime-ktx:3.2.0-alpha04"
    implementation "androidx.preference:preference-ktx:1.2.0"
    implementation "androidx.recyclerview:recyclerview:1.3.0-rc01"
    def room_version = "2.5.0"
    implementation "androidx.room:room-runtime:$room_version"
    ksp "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-paging:$room_version"
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"
    implementation "com.drakeet.drawer:drawer:1.0.3"
    implementation "com.google.android.material:material:1.8.0"
    implementation "com.squareup.okhttp3:okhttp-bom:5.0.0-alpha.11"
    implementation "com.squareup.okhttp3:okhttp-coroutines"
    implementation "dev.rikka.rikkax.appcompat:appcompat:1.5.0.1"
    implementation "dev.rikka.rikkax.core:core-ktx:1.4.1"
    //noinspection GradleDependency
    implementation "dev.rikka.rikkax.material:material:1.6.6"
    implementation "dev.rikka.rikkax.preference:simplemenu-preference:1.0.3"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
    implementation "org.jsoup:jsoup:1.15.3"
}

configurations.all {
    exclude group: "androidx.appcompat", module: "appcompat"
    exclude group: "org.jetbrains.kotlin", module: "kotlin-android-extensions-runtime"
    exclude group: "org.jetbrains.kotlin", module: "kotlin-stdlib-jdk7"
    exclude group: "org.jetbrains.kotlin", module: "kotlin-stdlib-jdk8"
}
