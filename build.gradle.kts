import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.1.RELEASE"
  id("io.spring.dependency-management") version "1.0.8.RELEASE"

  kotlin("jvm") version "1.3.71"
  kotlin("plugin.spring") version "1.3.71"
  kotlin("plugin.jpa") version "1.3.71"
  jacoco

  id("org.sonarqube") version "2.8"
}

group = "io.code.morning"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
  jcenter()
  mavenCentral()
  /*
  maven {
    url = uri("/Users/otajisan/.m2/repository")
  }
   */
}

dependencies {
  // Spring Boot
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  // Libraries
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.3")
  implementation("com.github.kittinunf.fuel:fuel:2.2.1")

  // Swagger
  implementation("org.springdoc:springdoc-openapi-kotlin:1.2.32")
  implementation("org.springdoc:springdoc-openapi-webflux-ui:1.2.32")

  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  // morning-code libs
  //implementation("io.code.morning:db:0.0.1-SNAPSHOT")

  // AWS X-Ray
  /*
  implementation("com.amazonaws:aws-xray-recorder-sdk-core")
  implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk")
  implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk-instrumentor")
   */
  // Testing
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("io.mockk:mockk:1.9")
  // Spring Cloud Contract
  //testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
  //testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
}

dependencyManagement {
  imports {
    mavenBom("com.amazonaws:aws-java-sdk-bom:1.11.67")
    mavenBom("com.amazonaws:aws-xray-recorder-sdk-bom:1.1.0")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

jacoco {
  toolVersion = "0.8.5"
  reportsDir = file("$buildDir/reports/jacoco")
}

tasks.jacocoTestReport {
  reports {
    xml.isEnabled = true
    html.isEnabled = false
  }

  dependsOn(tasks.withType<Test>())
}

sonarqube {
  properties {
    property("sonar.sourceEncoding", "UTF-8")
    property("sonar.projectKey", "api")
    property("sonar.sources", ".")
    property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
  }
}
