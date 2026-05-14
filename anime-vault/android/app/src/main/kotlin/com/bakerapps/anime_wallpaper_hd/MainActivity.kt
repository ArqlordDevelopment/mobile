package com.bakerapps.anime_wallpaper_hd

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.widget.Toast
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val channelName = "anime_wallpaper_hd/wallpaper"
    private val logTag = "AnimeWallpaper"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelName).setMethodCallHandler { call, result ->
            when (call.method) {
                "setWallpaper" -> {
                    val assetPath = call.argument<String>("assetPath")
                    val target = call.argument<String>("target")

                    if (assetPath.isNullOrBlank() || target.isNullOrBlank()) {
                        result.error("bad_args", "Missing wallpaper asset or target.", null)
                        return@setMethodCallHandler
                    }

                    try {
                        setWallpaperFromAsset(assetPath, target)
                        Toast.makeText(applicationContext, "Wallpaper applied", Toast.LENGTH_SHORT).show()
                        result.success(null)
                    } catch (error: Exception) {
                        Log.e(logTag, "Could not set wallpaper", error)
                        result.error(
                            "set_wallpaper_failed",
                            error.message ?: "Could not set wallpaper.",
                            null
                        )
                    }
                }
                "closeApp" -> {
                    result.success(null)
                    finishAndRemoveTask()
                }
                else -> result.notImplemented()
            }
        }
    }

    private fun setWallpaperFromAsset(assetPath: String, target: String) {
        val assetKey = FlutterInjector.instance().flutterLoader().getLookupKeyForAsset(assetPath)
        val manager = WallpaperManager.getInstance(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            when (target) {
                "home" -> setBitmapForTarget(manager, assetKey, WallpaperManager.FLAG_SYSTEM)
                "lock" -> setBitmapForTarget(manager, assetKey, WallpaperManager.FLAG_LOCK)
                "both" -> {
                    setBitmapForTarget(manager, assetKey, WallpaperManager.FLAG_SYSTEM)
                    setBitmapForTarget(manager, assetKey, WallpaperManager.FLAG_LOCK)
                }
                else -> throw IllegalArgumentException("Unknown wallpaper target: $target.")
            }
        } else {
            val bitmap = decodeAsset(assetKey)
            manager.setBitmap(bitmap)
        }

        Log.d(logTag, "Wallpaper set from $assetPath for $target")
    }

    private fun setBitmapForTarget(manager: WallpaperManager, assetKey: String, flag: Int) {
        val bitmap = decodeAsset(assetKey)
        manager.setBitmap(bitmap, null, true, flag)
    }

    private fun decodeAsset(assetKey: String) =
        assets.open(assetKey).use { stream ->
            BitmapFactory.decodeStream(stream)
                ?: throw IllegalStateException("Could not decode wallpaper image.")
        }
}
