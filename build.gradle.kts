import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "me.joelj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.webjars.bowergithub.firebase:firebase-bower:2.4.2")
    implementation("com.google.firebase:firebase-admin:8.1.0")
//    implementation("com.google.firebase:firebase-firestore:24.0.1")
    testImplementation(kotlin("test"))

    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:29.0.0"))

    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-firestore-ktx:24.0.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}