plugins {
    id("fabric-loom") version "1.9-SNAPSHOT"
    id("maven-publish")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

version = project.findProperty("mod_version") as String
group = project.findProperty("maven_group") as String

base {
    archivesName.set(project.findProperty("archives_base_name") as String)
}

val shadowModImpl by configurations.creating {
    configurations.modImplementation.get().extendsFrom(this)
}

repositories {
    mavenLocal()
    maven("https://maven.notenoughupdates.org/releases/")
    maven( "https://maven.fabricmc.net/")
    mavenCentral()
}

dependencies {
    "minecraft"("com.mojang:minecraft:${project.findProperty("minecraft_version")}")
    "mappings"("net.fabricmc:yarn:${project.findProperty("yarn_mappings")}:v2")
    "modImplementation"("net.fabricmc:fabric-loader:${project.findProperty("loader_version")}")
    "modImplementation"("net.fabricmc.fabric-api:fabric-api:${project.findProperty("fabric_version")}")
    "modImplementation"("net.fabricmc:fabric-language-kotlin:1.13.0+kotlin.2.1.0")
    implementation("com.google.code.gson:gson:2.11.0")
    //"modImplementation"("org.notenoughupdates.moulconfig:modern:3.5.0")
    "shadowModImpl"("org.notenoughupdates.moulconfig:modern:3.5.0")
}

tasks.shadowJar {
    configurations = listOf(shadowModImpl)
    relocate("io.github.notenoughupdates.moulconfig", "io.github.frostzie.examplemod.deps.moulconfig")
}

tasks.named<ProcessResources>("processResources") {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile> {
    options.release.set(21)
}

java {

    withSourcesJar()
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.findProperty("archives_base_name") as String
            from(components["java"])
        }
    }

    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}