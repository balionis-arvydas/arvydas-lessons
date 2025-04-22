import com.github.davidmc24.gradle.plugin.avro.GenerateAvroProtocolTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroSchemaTask
import com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask

plugins {
    id("java")
    id("com.github.davidmc24.gradle.plugin.avro-base") version ("1.2.0")
}

dependencies {
    implementation(Libs.AVRO)
    implementation(Libs.AVRO_TOOLS)
}

tasks.register("generateAvroProtocol", GenerateAvroProtocolTask::class) {
    source(file("src/main/resources/avro"))
    include("**/*.avdl")
    setOutputDir(file("${buildDir}/generated/src/main/resources/avro"))
}

tasks.register("generateAvroSchema", GenerateAvroSchemaTask::class) {
    dependsOn(tasks["generateAvroProtocol"])
    source(file("${buildDir}/generated/src/main/resources/avro"))
    include("**/*.avpr")
    setOutputDir(file("${buildDir}/generated/src/main/resources/avro"))
}

tasks.register("generateAvroJava", GenerateAvroJavaTask::class) {
    dependsOn(tasks["generateAvroSchema"])
    source(file("${buildDir}/generated/src/main/resources/avro"))
    include("**/*.avsc")
    setOutputDir(file("${buildDir}/generated/src/main/java"))
}

sourceSets {
    main {
        java {
            srcDirs(listOf("${buildDir}/generated/src/main/java"))
        }
    }
}

tasks.compileJava {
    dependsOn(tasks["generateAvroJava"])
}
