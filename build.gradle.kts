/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.0.1/userguide/tutorial_java_projects.html
 */

group = "com.scottlogic.datahelix.generator"

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application.
    application

    id("org.openjfx.javafxplugin") version "0.0.8"
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:28.0-jre")

    implementation("org.controlsfx:controlsfx:11.0.1")
    implementation("org.controlsfx:openjfx-dialogs:1.0.3")

    // Use JUnit test framework
    testImplementation("junit:junit:4.12")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    // Define the main class for the application.
    mainClassName = "com.scottlogic.datahelix.generator.DataHelixRunnerMain"
}

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.fxml")
}
