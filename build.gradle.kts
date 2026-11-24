import org.gradle.kotlin.dsl.implementation

plugins {
    java
    id("org.springframework.boot") version "4.1.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "SpringAI"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    runtimeOnly("org.postgresql:postgresql")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testCompileOnly("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testAnnotationProcessor("org.projectlombok:lombok")
    // Source: https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-model
    implementation("org.springframework.ai:spring-ai-model:2.0.2")
    // Source: https://mvnrepository.com/artifact/org.springframework.ai/spring-ai-client-chat
    implementation("org.springframework.ai:spring-ai-client-chat:2.0.2")
    implementation("org.springframework.ai:spring-ai-starter-model-openai:2.0.2")
    implementation("org.springframework.ai:spring-ai-starter-model-anthropic:2.0.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaExec>().configureEach {
    val envFile = rootProject.file(".env")
    if (envFile.exists()) {
        envFile.readLines()
            .map { it.trim() }
            .filter { it.isNotEmpty() && !it.startsWith("#") && it.contains("=") }
            .forEach { line ->
                val parts = line.split("=", limit = 2)
                environment(parts[0].trim(), parts[1].trim())
            }
    }
}
