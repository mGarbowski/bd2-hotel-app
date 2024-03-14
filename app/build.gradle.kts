plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("application")
}

group = "pl.mgarbowski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.3.8")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "pl.mgarbowski.Main"
}
