plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.epod"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.epod"
        minSdk = 21
        targetSdk = 33
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

        viewBinding {
            enable = true
        }
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main") {
            res {
                srcDirs(
                    "src\\main\\res",
                    "src\\main\\res\\anim",
                )
            }
        }
    }
}

dependencies {
    val room_version = "2.6.1";

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")

//    kapt ("androidx.room:room-compiler:$room_version")
//    ksp ("androidx.room:room-compiler:$room_version")

    implementation ("androidx.room:room-rxjava2:$room_version")

    implementation ("androidx.room:room-rxjava3:$room_version")

    implementation ("androidx.room:room-guava:$room_version")

    testImplementation ("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation ("androidx.room:room-paging:$room_version")

    implementation ("com.google.dagger:dagger:2.52")
    annotationProcessor ("com.google.dagger:dagger-compiler:2.52")
}