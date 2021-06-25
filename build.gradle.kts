plugins {
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}
// 参考 Maven Publish Plugin
// https://docs.gradle.org/current/userguide/publishing_maven.html#publishing_maven
// 参考 volley/publish.gradle at master · google/volley
// https://github.com/google/volley/blob/master/publish.gradle
group = "com.jacknic.packages"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Jacknic/github-packages")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        publications {
            create<MavenPublication>("gpr") {
                from(components["java"])
            }
        }
    }
}