package com.bakerapps.miniworth.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bakerapps.miniworth.components.BottomNavBar
import com.bakerapps.miniworth.data.MockProducts
import com.bakerapps.miniworth.screens.CollectionScreen
import com.bakerapps.miniworth.screens.HomeScreen
import com.bakerapps.miniworth.screens.ResaleScreen
import com.bakerapps.miniworth.screens.ScanResultScreen
import com.bakerapps.miniworth.screens.ScanScreen
import com.bakerapps.miniworth.screens.SearchScreen
import com.bakerapps.miniworth.ui.theme.MiniBackground

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val recentScanIds = remember { mutableStateListOf<Int>() }

    fun recordRecentScan(productId: Int) {
        recentScanIds.remove(productId)
        recentScanIds.add(0, productId)
        while (recentScanIds.size > 5) {
            recentScanIds.removeAt(recentScanIds.lastIndex)
        }
    }

    fun openResult(productId: Int, recordAsScan: Boolean = false) {
        if (recordAsScan) {
            recordRecentScan(productId)
        }
        navController.navigate(Route.ScanResult.create(productId))
    }

    Scaffold(
        containerColor = MiniBackground,
        bottomBar = {
            BottomNavBar(currentDestination = currentDestination) { route ->
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.path,
            modifier = Modifier.padding(padding)
        ) {
            composable(Route.Home.path) {
                HomeScreen(
                    recentScans = recentScanIds.map { MockProducts.product(it) },
                    onScan = { navController.navigate(Route.Scan.path) },
                    onSearch = { navController.navigate(Route.Search.path) },
                    onProduct = { openResult(it) }
                )
            }
            composable(Route.Scan.path) {
                ScanScreen(
                    onBack = { navController.popBackStack() },
                    onResult = { openResult(productId = 1, recordAsScan = true) }
                )
            }
            composable(Route.Collection.path) {
                CollectionScreen(onProduct = { openResult(it) })
            }
            composable(Route.Search.path) {
                SearchScreen(onProduct = { openResult(it) })
            }
            composable(
                route = Route.ScanResult.path,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { entry ->
                val productId = entry.arguments?.getInt("productId") ?: 1
                ScanResultScreen(
                    product = MockProducts.product(productId),
                    onBack = { navController.popBackStack() },
                    onResale = { navController.navigate(Route.Resale.create(it)) }
                )
            }
            composable(
                route = Route.Resale.path,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { entry ->
                val productId = entry.arguments?.getInt("productId") ?: 1
                ResaleScreen(
                    product = MockProducts.product(productId),
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
