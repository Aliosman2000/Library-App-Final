plugins {
    /*
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

     */

    id("com.android.application")
    //id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.android")
    // Room için gerekli KSP eklentisi. Bu satır olmadan ksp bağımlılığı çalışmaz.
    id("com.google.devtools.ksp")



}

android {
    namespace = "com.egitim.kutuphee_yazilimi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.egitim.kutuphee_yazilimi"
        minSdk = 25
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

val room_version = "2.6.1"
val lifecycle_version = "2.6.2"
val coroutines_version = "1.7.3"

dependencies {


    // Room runtime
    implementation("androidx.room:room-runtime:$room_version")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.play.services.cast.framework)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // KSP (Kotlin Symbol Processing) ile derleyici
    ksp("androidx.room:room-compiler:$room_version")
    // Kotlin Coroutine desteği (isteğe bağlı ama önerilir)
    implementation("androidx.room:room-ktx:$room_version")




    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines_version}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines_version}")

    /*
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

     */





    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
