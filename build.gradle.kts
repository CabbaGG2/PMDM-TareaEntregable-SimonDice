// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Esta línea define la versión 2.0.21 que necesitamos para Room
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false

    // Estos alias mantienen el resto de la configuración de Android y Compose
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}