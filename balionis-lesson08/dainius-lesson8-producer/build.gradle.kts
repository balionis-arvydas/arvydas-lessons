import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.openapi.generator") version "7.2.0"
}

dependencies {

    compileOnly(Libs.LOMBOK)
    annotationProcessor(Libs.LOMBOK)

    implementation(Libs.SLF4J_API)
    runtimeOnly(Libs.LOGBACK_CORE)
    runtimeOnly(Libs.LOGBACK_CLASSIC)

    testImplementation(Libs.JUNIT_JUPITER_API)
    testRuntimeOnly(Libs.JUNIT_JUPITER_ENGINE)

    testImplementation(Libs.MOCKITO_CORE)
    testImplementation(Libs.MOCKITO_JUNIT_JUPITER)

    implementation(Libs.SPRING_BOOT_STARTER_WEB) {
        exclude(Libs.SPRING_BOOT_STARTER_TOMCAT)
    }
    implementation(Libs.SPRING_BOOT_STARTER_JETTY)
    testImplementation(Libs.SPRING_BOOT_STARTER_TEST)
    testImplementation(Libs.SPRING_BOOT_STARTER_WEBFLUX)
    annotationProcessor(Libs.SPRING_BOOT_CONFIGURATION_PROCESSOR)

    compileOnly(Libs.JAVAX_SERVLET_API)
    implementation(Libs.SWAGGER_ANNOTATIONS)
    implementation(Libs.JAVAX_VALIDATION_API)
    implementation(Libs.JAVAX_ANNOTATION_API)

    implementation(Libs.SPRING_KAFKA)

    implementation(Libs.MAPSTRUCT_CORE)
    annotationProcessor(Libs.MAPSTRUCT_PROCESSOR)
}

openApiValidate {
    inputSpec.set("$projectDir/specs/dainius-lesson8-producer-api.yaml")
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$projectDir/specs/dainius-lesson8-producer-api.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.balionis.dainius.lesson8.producer.generated.api")
    modelPackage.set("com.balionis.dainius.lesson8.producer.generated.model")
    configOptions.set(mapOf(
        "dateLibrary" to "java8",
        "generateApis" to "true",
        "generateApiTests" to "false",
        "generateModels" to "true",
        "generateModelTests" to "false",
        "generateModelDocumentation" to "false",
        "generateSupportingFiles" to "false",
        "hideGenerationTimestamp" to "true",
        "interfaceOnly" to "true",
        "library" to "spring-boot",
        "serializableModel" to "true",
        "useBeanValidation" to "true",
        "useTags" to "true",
        "implicitHeaders" to "true",
        "openApiNullable" to "false",
        "oas3" to "true"
    ))
}

sourceSets {
    main {
        java {
            srcDirs(listOf("${buildDir}/generated/src/main/java"))
        }
    }
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
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
            excludes = listOf(
                "com.balionis.dainius.lesson8.producer.Application",
                "com.balionis.dainius.lesson8.producer.configuration.*",
                "com.balionis.dainius.lesson8.producer.generated.*",
                "com.balionis.dainius.lesson8.producer.stream.mappers.impl.*"
            )
        }
    }
    dependsOn(tasks.test)
    finalizedBy(tasks.jacocoTestReport)
}

tasks.named<BootJar>("bootJar") {
    mainClass.set("com.balionis.dainius.lesson8.producer.Application")
    archiveFileName.set("${archiveBaseName.get()}-all.${archiveExtension.get()}");
}
