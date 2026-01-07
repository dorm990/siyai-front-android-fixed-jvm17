plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

    // Firebase
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.performance)
}


// Apply Firebase plugins only when google-services.json exists.
// This prevents build failures on clean clones.
if (file("google-services.json").exists()) {
    apply(plugin = "com.google.gms.google-services")
    apply(plugin = "com.google.firebase.crashlytics")
    apply(plugin = "com.google.firebase.firebase-perf")
    apply(plugin = "com.google.firebase.appdistribution")
}

android {
    namespace = "com.example.siyai"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.siyai"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }



    buildTypes {
        debug {}
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures { compose = true }
    kotlinOptions { jvmTarget = "17" }

}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:db"))
    implementation(project(":core:analytics"))

    implementation(project(":feature:auth:impl"))
    implementation(project(":feature:feed:impl"))
    implementation(project(":feature:details:impl"))
    implementation(project(":feature:favorites:impl"))

    implementation(platform(libs.compose.bom))
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.hilt)

    implementation(libs.appcompat)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)
    implementation(platform("com.google.firebase:firebase-bom:34.7.0"))

    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-perf")

}



plugins.withId("com.google.firebase.appdistribution") {
    // Optional: configure via gradle.properties or CI secrets
    extensions.configure<com.google.firebase.appdistribution.gradle.AppDistributionExtension> {
        val appIdProp = (project.findProperty("firebaseAppId") as String?)
        val groupsProp = (project.findProperty("firebaseGroups") as String?)
        if (!appIdProp.isNullOrBlank()) appId = appIdProp
        if (!groupsProp.isNullOrBlank()) groups = groupsProp

    }
}
dependencies {
    implementation(project(":feature:auth:api"))
    implementation(project(":feature:auth:impl"))

    implementation(project(":feature:feed:api"))
    implementation(project(":feature:feed:impl"))

    implementation(project(":feature:details:api"))
    implementation(project(":feature:details:impl"))

    implementation(project(":feature:favorites:api"))
    implementation(project(":feature:favorites:impl"))
}

