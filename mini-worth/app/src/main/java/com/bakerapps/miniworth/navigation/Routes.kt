package com.bakerapps.miniworth.navigation

sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Scan : Route("scan")
    data object Collection : Route("collection")
    data object Search : Route("search")
    data object ScanResult : Route("scanResult/{productId}") {
        fun create(productId: Int) = "scanResult/$productId"
    }
    data object Resale : Route("resale/{productId}") {
        fun create(productId: Int) = "resale/$productId"
    }
}
