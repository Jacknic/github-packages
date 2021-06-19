plugins {
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}

group = "com.jacknic.packages"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
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
            create("gpr", MavenPublication::class.java) {
                from(components["java"])
            }
        }
    }
}