import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

plugins {
    id("com.android.application")
}

android {
    namespace = "com.lionor.royaldiver"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.lionor.royaldiver"
        minSdk = 33
        targetSdk = 35
        versionCode = 3
        versionName = "1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("../lionor-release.jks")
            storePassword = "L10n0r@Diver2026!"
            keyAlias = "lionor"
            keyPassword = "L10n0r@Diver2026!"
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

// WFF bundles must contain no dex files — strip them automatically after every release bundle build
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
                println("WFF dex strip: removed $removed dex file(s) from ${aab.name}")
            } else {
                tmp.delete()
                println("WFF dex strip: no dex files found, bundle is already clean")
            }
        }
    }
}
