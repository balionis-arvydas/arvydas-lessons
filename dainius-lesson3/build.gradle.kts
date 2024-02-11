import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("application")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.slf4j:slf4j-api:2.0.11")
    runtimeOnly("ch.qos.logback:logback-core:1.4.14")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.14")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

jacoco {
    toolVersion = "0.8.9"
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "1G"

    testLogging {
        showStandardStreams = true
        events("passed")
    }

    finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            element = "CLASS"
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.90".toBigDecimal()
            }
            excludes = listOf("com.balionis.dainius.lesson3.Application",
                "com.balionis.dainius.lesson3.configuration.*")
        }
    }
    dependsOn(tasks.test)
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacoco-html")
    }

    dependsOn(tasks.test)
}

tasks.named<BootJar>("bootJar") {
    mainClass.set("com.balionis.dainius.lesson3.Application")
    archiveFileName.set("${archiveBaseName.get()}-all.${archiveExtension.get()}");
}

application {
    mainClass.set("com.balionis.dainius.lesson3.Application")
}

tasks.named<JavaExec>("run") {
    args("3", "1", "2", "3", "5")
}