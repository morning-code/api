import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "io.code.morning"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	//implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// AWS X-Ray
	implementation("com.amazonaws:aws-xray-recorder-sdk-core")
	implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk")
	implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk-instrumentor")
	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
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
