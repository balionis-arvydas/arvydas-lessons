allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://packages.confluent.io/maven/")
        }
    }
}

plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot") version "3.3.4" apply false
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.13.0" apply false
}

val javaProjects = setOf(
    "arvydas-lesson12-producer-app",
    "arvydas-lesson12-consumer-app"
)

val apiProjects = setOf(
    "arvydas-lesson12-producer-api",
    "arvydas-lesson12-consumer-api"
)

val dockerProjects = setOf(
    "arvydas-lesson12-producer-app",
    "arvydas-lesson12-consumer-app"
)

subprojects {

    if (javaProjects.contains(this.project.name)) {
        apply {
            plugin("java")
            plugin("jacoco")
            plugin("org.springframework.boot")
            plugin("io.spring.dependency-management")
            plugin("org.openapi.generator")
        }

        java {
            sourceCompatibility = JavaVersion.VERSION_17
        }

        configurations {
            compileOnly {
                extendsFrom(configurations.annotationProcessor.get())
            }
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

        jacoco {
            toolVersion = "0.8.9"
        }

        tasks.jacocoTestReport {
            reports {
                xml.required.set(true)
                csv.required.set(false)
            }
        }
    }

    if (apiProjects.contains(this.project.name)) {
        apply {
            plugin("java-library")
            plugin("org.springframework.boot")
            plugin("io.spring.dependency-management")
            plugin("org.openapi.generator")
        }

        java {
            sourceCompatibility = JavaVersion.VERSION_17
        }
    }

    if (dockerProjects.contains(this.project.name)) {
        val dockerImage = "${this.project.name}".replace("-app","")
        extra["dockerImage"] = dockerImage

        tasks {
            val dockerTags = listOf("latest")

            register<Exec>("dockerBuild") {
                dependsOn("build")

                description = "Builds a service's docker image."
                group = "Docker"

                environment("DOCKER_BUILDKIT", "1")
                commandLine(
                    listOf("docker", "build") +
                            dockerTags.flatMap { listOf("-t", "$dockerImage:$it") } +
                            project.projectDir.absolutePath
                )
            }
        }
    }
}
