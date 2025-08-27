// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    id("com.android.application") version "8.7.0" apply false
    // Kotlin versiyonunu stabil 1.9.22'ye geri alıyoruz.
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.28" apply false

    /*
    id("com.android.application") version "8.12.0" apply false
    id("com.android.library") version "8.12.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false

     */
    //id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false



    /*
    alias(libs.plugins.android.application) version "8.2.0" apply false
    alias(libs.plugins.kotlin.android) version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false

     */
}