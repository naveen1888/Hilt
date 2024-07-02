// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.kspKotlinAndroid) apply false
    alias(libs.plugins.hiltKotlinAndroid) apply false
    alias(libs.plugins.roomKotlinAndroid) apply false
}