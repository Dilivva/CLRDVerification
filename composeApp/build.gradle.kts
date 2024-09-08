import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.serialization)
    alias(libs.plugins.buildKonfig)
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
            useCommonJs()
        }
        binaries.executable()
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(libs.kamel)
            implementation(libs.firebase.firestore)
        }
    }
}

compose.experimental {
    web.application {}
}

buildkonfig {
    packageName = ""
    objectName = "CLRDConfig"

    defaultConfigs{
        val applicationId = project.findProperty("applicationId") as String? ?: "MAPS_DiLivva"
        val apiKey = project.findProperty("apiKey") as String? ?: "keys"
        buildConfigField(STRING, "APPLICATION_ID", applicationId)
        buildConfigField(STRING, "API_KEY", apiKey)
    }
}