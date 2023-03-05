plugins {
    id("java")
    id("jvm-test-suite")
}

group = "sdu.dk.group.one"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation(project(mapOf("path" to ":core")))
    testImplementation(project(mapOf("path" to ":desktop")))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed")
    }
    filter {
        //include all tests from package
        includeTestsMatching("suite.*")
    }
}

//testing {
//    suites {
//        val test by getting(JvmTestSuite::class) {
//            useJUnitJupiter()
//            sources {
//                java {
//                    setSrcDirs(listOf("tests/src/test/java/implementations"))
//                }
//            }
//        }
//        val integrationTest by registering(JvmTestSuite::class) {
//            dependencies {
//                implementation(project)
//            }
//
//            targets {
//                all {
//                    testTask.configure {
//                        shouldRunAfter()
//                    }
//                }
//            }
//        }
//    }
//}
//tasks.named("check") {
//    dependsOn(testing.suites.named("integrationTest"))
//}

