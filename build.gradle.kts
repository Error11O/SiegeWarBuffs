plugins {
    kotlin("jvm") version "2.2.0-Beta2"
    id("com.gradleup.shadow") version "8.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.Error110"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.mikeprimm.com/") {
        name = "dynmap-repo"
    }
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.glaremasters.me/repository/towny/") {
        name = "glaremasters repo"
    }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    compileOnly ("org.spigotmc:spigot-api:1.21.3-R0.1-SNAPSHOT")
    compileOnly ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly ("com.palmergames.bukkit.towny:towny:0.101.1.0")
    compileOnly ("com.github.TownyAdvanced:SiegeWar:2.19.2")
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.21")
    }
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
