plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

}

android {
    namespace = "com.example.core.common"
    compileSdk = 35
    defaultConfig { minSdk = 26 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }




}
dependencies {
    // Coroutines (DispatchersProvider etc.)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // @Inject annotation without pulling full DI runtime
    implementation(libs.javax.inject)
}
