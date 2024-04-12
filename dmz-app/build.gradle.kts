val kotlinx_serialization_version: String by project
val ktor_version: String by project
val kotlin_version: String by project
val ktor_server_config_yaml_version: String by project
val logback_version: String by project

val exposed_version: String by project
val postgresql_version: String by project
val solrj_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
    kotlin("plugin.serialization") version "1.9.23"
}

group = "ch.zindustries"
version = "0.0.1"

application {
    mainClass.set("ch.zindustries.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    /* Server dependencies */
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_serialization_version")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml-jvm:$ktor_server_config_yaml_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    /* Postgres */
    implementation("org.postgresql:postgresql:$postgresql_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

    /* Solr */
    implementation("org.apache.solr:solr-solrj:$solrj_version")

    /* Testing */
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
