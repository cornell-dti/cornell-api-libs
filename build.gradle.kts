import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    java
    kotlin(module = "jvm") version "1.3.60"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    jcenter()
}

version = "0.5"

dependencies {
    implementation(kotlin(module = "stdlib-jdk8"))
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    testImplementation(dependencyNotation = "org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation(dependencyNotation = "org.junit.jupiter:junit-jupiter-params:5.5.2")
    testImplementation(dependencyNotation = "org.junit.jupiter:junit-jupiter-engine:5.5.2")
    implementation(dependencyNotation = "com.google.code.gson:gson:2.8.6")
    implementation(dependencyNotation = "com.github.kittinunf.fuel:fuel:2.2.1")
}

tasks {
    compileJava {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<KotlinJvmCompile> {
        kotlinOptions.jvmTarget = "11"
        kotlinOptions.freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
    named<Test>(name = "test") {
        useJUnitPlatform()
        testLogging {
            events("skipped", "failed")
        }
    }
    shadowJar {
        minimize()
        manifest { attributes["Main-Class"] = "api.cornell.Main" }
        isZip64 = true
        artifacts {
            shadow(archiveFile) { builtBy(shadowJar) }
        }
    }
    "assemble" { dependsOn(shadowJar) }
}

