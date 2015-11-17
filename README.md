gradle-android-webp
=============================

This is a plugin for Android Gradle to automatically covert PNG resources to WEBP.

## Pre-requirement
This plugin requires WEBP command line utility ```cwebp```. Download and install it from [WEBP](https://developers.google.com/speed/webp/). Make sure ```cwebp``` is in your path.

## Usage
1. Maven url of this plugin is: "http://dl.bintray.com/julianshen/maven". Add repository setting in your Android project's top-level `build.gradle` file:
```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/julianshen/maven" 
    }
}
```
2. Also add dependency in your Android project's top-level `build.gradle` file:
```groovy
buildscript {
    dependencies {
        classpath "wtf.cowbay.gradlewebp:gradle-android-webp:1.0"
    }
}
```
3. Apply the plugin in your app module's ``build.gradle`` file:
```groovy
apply plugin: 'wtf.cowbay.gradlewebp'

androidwebp {
   cwebp "/path/to/cwebp" /* optional */
   quality 70 /* Image quality */
}
```
