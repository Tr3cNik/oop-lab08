plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "1.155.0"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("it.unibo.mvc.LaunchApp")
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
}

tasks.javadoc {
    isFailOnError = false
}
