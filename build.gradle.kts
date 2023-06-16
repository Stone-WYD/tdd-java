plugins {
    id("java")
}

group = "com.wyd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // testImplementation("junit:junit:4.11")
    implementation("org.jongo:jongo:1.1")
    implementation("org.mongodb:mongo-java-driver:2.+")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
    testImplementation("org.mockito:mockito-all:1.+")
}

tasks.test {
    useJUnitPlatform()
}
