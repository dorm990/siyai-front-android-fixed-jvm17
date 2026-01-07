plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

}

android {
    namespace = "com.example.feature.auth.api"
    compileSdk = 35
    defaultConfig { minSdk = 26 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions { jvmTarget = "17" }

}
dependencies {
    // API exposes Voyager Screen type
    implementation(libs.voyager.navigator)

    // Quick wiring: expose implementation through api module
    implementation(project(":feature:auth:impl"))
}
