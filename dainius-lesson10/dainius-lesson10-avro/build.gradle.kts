import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask

plugins {
    id("java-library")
    id("com.github.davidmc24.gradle.plugin.avro-base") version ("1.2.0")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libs.AVRO)
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
