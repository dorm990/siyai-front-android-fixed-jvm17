import org.gradle.api.JavaVersion
import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // base android/kotlin plugins (versions via libs.versions.toml)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.compose) apply false

    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false

    // Firebase plugins (declared here, applied in app module)
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.performance) apply false
    alias(libs.plugins.firebase.appdistribution) apply false

    // Detekt (root declares, modules can apply OR we apply to all below)
    id("io.gitlab.arturbosch.detekt") version "1.23.6" apply false
}

subprojects {
    // --- JVM 17 everywhere (fixes 1.8 vs 17 issues with KSP) ---
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "17"
    }

    // --- Detekt for all modules (so `./gradlew detekt` works) ---
    // Safe: we apply plugin, and configure it via extensions.configure (no "detekt {}" accessor)
    apply(plugin = "io.gitlab.arturbosch.detekt")

    plugins.withId("io.gitlab.arturbosch.detekt") {
        extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
            // If you don't have detekt.yml yet, you can comment this line
            config.setFrom(files("${rootDir}/detekt.yml"))
            buildUponDefaultConfig = true
            allRules = false
            parallel = true
        }
    }
}
