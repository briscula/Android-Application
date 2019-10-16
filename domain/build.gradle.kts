plugins {
    kotlin("multiplatform")
}

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js{
        browser()
    }

    sourceSets {

        val kotlinVersion: String by project
        val coroutinesVersion: String by project
        val klockVersion: String by project

        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib-common", kotlinVersion))
                api("com.soywiz.korlibs.klock:klock:$klockVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")
            }
        }

        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8", kotlinVersion))
                api("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js", kotlinVersion))
                api("com.soywiz.korlibs.klock:klock-js:$klockVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
            }
        }

        all {
            languageSettings.useExperimentalAnnotation("kotlin.Experimental")
        }

    }

}
