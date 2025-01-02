plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.app.hearme"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.app.hearme"
        minSdk = 26
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("com.google.firebase:firebase-storage:20.0.1")
    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.android.gms:play-services-fido:20.1.0")
    implementation("androidx.media3:media3-session:1.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //View model
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    //Text box country code number
    implementation("com.hbb20:ccp:2.7.3")

    //Pin view
    implementation("io.github.chaosleung:pinview:1.4.4")

    // viewpager
    implementation("androidx.viewpager2:viewpager2:1.0")

    // room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    androidTestImplementation("androidx.room:room-testing:2.5.2")

    //Text box show and less more
    implementation("kr.co.prnd:readmore-textview:1.0.0")

    //Picasso
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("jp.wasabeef:picasso-transformations:2.4.0")

    //Media
    implementation("androidx.media:media:1.5.0")
    implementation("androidx.media3:media3-exoplayer:1.1.1")
    implementation("androidx.media3:media3-ui:1.1.1")
    implementation("androidx.media3:media3-common:1.1.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.8.0")
    implementation("com.github.bumptech.glide:compiler:4.8.0")

    //Biometric
    implementation("androidx.biometric:biometric-ktx:1.2.0-alpha05")

    //Read json animated button
    implementation("com.airbnb.android:lottie:3.4.4")

    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:x.x.x")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:x.x.x")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("com.caverock:androidsvg:1.4")
}

