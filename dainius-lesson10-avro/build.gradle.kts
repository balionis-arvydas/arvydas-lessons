import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask

plugins {
    id("java")
    id("com.github.davidmc24.gradle.plugin.avro-base") version ("1.2.0")
    id("maven-publish")
    id("org.ajoberstar.grgit") version ("4.1.1")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.avro:avro:1.11.4")
}

tasks.register("generateAvroProtocol", GenerateAvroProtocolTask::class) {
    source(file("src/main/resources/avro"))
    include("**/*.avdl")
    setOutputDir(file("$projectDir/build/generated/src/main/resources/avro"))
}

tasks.register("generateAvroSchema", GenerateAvroSchemaTask::class) {
    dependsOn(tasks["generateAvroProtocol"])
    source(file("$projectDir/build/generated/src/main/resources/avro"))
    include("**/*.avpr")
    setOutputDir(file("$projectDir/build/generated/src/main/resources/avro"))
}

tasks.register("generateAvroJava", GenerateAvroJavaTask::class) {
    dependsOn(tasks["generateAvroSchema"])
    source(file("$projectDir/build/generated/src/main/resources/avro"))
    include("**/*.avsc")
    setOutputDir(file("$projectDir/build/generated/src/main/java"))
}

sourceSets {
    main {
        java {
            srcDirs(listOf("$projectDir/build/generated/src/main/java"))
        }
    }
}

tasks.compileJava {
    dependsOn(tasks["generateAvroJava"])
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.balionis"
            artifactId = "dainius-lesson10-avro"
            version = getApiVersion()

            from(components["java"])
        }
    }
}

fun getApiVersion() : String {
    val apiVersion = project.findProperty("API_VERSION").toString()
    val buildNumber = System.getenv("BUILD_NUMBER")
    val hashcode = grgit.head().abbreviatedId
    return (apiVersion + "-" + (buildNumber ?: hashcode))
}
