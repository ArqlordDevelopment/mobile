import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

plugins {
    id("com.android.application")
}

android {
    namespace = "com.lionor.animedigitalfaces"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.lionor.animedigitalfaces"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("../anime-digital-release.jks")
            storePassword = "An1m3D1g1tal@2026!"
            keyAlias = "animedigital"
            keyPassword = "An1m3D1g1tal@2026!"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
        }
    }

    androidResources {
        noCompress += "png"
    }
}

// WFF: strip dex from AAB after bundleRelease — Play Store rejects watch face bundles with dex files
afterEvaluate {
    tasks.named("bundleRelease") {
        doLast {
            val aab = file("build/outputs/bundle/release/app-release.aab")
            if (!aab.exists()) return@doLast
            val tmp = file("build/outputs/bundle/release/app-release-nodex.aab")
            val zipIn  = ZipFile(aab)
            val zipOut = ZipOutputStream(tmp.outputStream().buffered())
            var removed = 0
            zipIn.entries().asSequence().forEach { entry: ZipEntry ->
                if (entry.name.endsWith(".dex")) {
                    removed++
                    println("WFF dex strip: removing ${entry.name}")
                } else {
                    zipOut.putNextEntry(ZipEntry(entry.name))
                    zipIn.getInputStream(entry).copyTo(zipOut)
                    zipOut.closeEntry()
                }
            }
            zipIn.close()
            zipOut.close()
            if (removed > 0) {
                aab.delete()
                tmp.renameTo(aab)
                println("WFF dex strip: removed $removed dex file(s) — AAB is Play Store ready")
            } else {
                tmp.delete()
            }
        }
    }
}
