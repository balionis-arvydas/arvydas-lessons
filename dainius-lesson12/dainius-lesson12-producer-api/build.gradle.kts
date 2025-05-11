import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {

    compileOnly(Libs.SPRING_BOOT_STARTER_WEB)
    compileOnly(Libs.JAVAX_SERVLET_API)
    compileOnly(Libs.SWAGGER_ANNOTATIONS)
    compileOnly(Libs.JAVAX_VALIDATION_API)
    compileOnly(Libs.JAVAX_ANNOTATION_API)
}

openApiValidate {
    inputSpec.set("$projectDir/src/main/resources/openapi/dainius-lesson12-producer-api.yaml")
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$projectDir/src/main/resources/openapi/dainius-lesson12-producer-api.yaml")
    outputDir.set("${layout.buildDirectory.get()}/generated")
    apiPackage.set("com.balionis.dainius.lesson12.producer.generated.api")
    modelPackage.set("com.balionis.dainius.lesson12.producer.generated.model")
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
            srcDirs(listOf("${layout.buildDirectory.get()}/generated/src/main/java"))
        }
    }
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}