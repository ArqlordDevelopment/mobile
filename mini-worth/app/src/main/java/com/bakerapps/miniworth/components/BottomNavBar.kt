package com.bakerapps.miniworth.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.CollectionsBookmark
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.bakerapps.miniworth.navigation.Route
import com.bakerapps.miniworth.ui.theme.MiniCardBorder
import com.bakerapps.miniworth.ui.theme.MiniNavBackground
import com.bakerapps.miniworth.ui.theme.MiniPrimary
import com.bakerapps.miniworth.ui.theme.MiniTextSecondary

data class BottomTab(val label: String, val route: String, val icon: ImageVector)

private val bottomTabs = listOf(
    BottomTab("Home", Route.Home.path, Icons.Rounded.Home),
    BottomTab("Scan", Route.Scan.path, Icons.Rounded.CameraAlt),
    BottomTab("Collection", Route.Collection.path, Icons.Rounded.CollectionsBookmark),
    BottomTab("Search", Route.Search.path, Icons.Rounded.Search)
)

@Composable
fun BottomNavBar(
    currentDestination: NavDestination?,
    onNavigate: (String) -> Unit
) {
    Column {
        HorizontalDivider(color = MiniCardBorder)
        NavigationBar(containerColor = MiniNavBackground, contentColor = MiniTextSecondary) {
            bottomTabs.forEach { tab ->
                val selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = { onNavigate(tab.route) },
                    icon = { Icon(tab.icon, contentDescription = tab.label) },
                    label = { Text(tab.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MiniPrimary,
                        selectedTextColor = MiniPrimary,
                        indicatorColor = MiniCardBorder,
                        unselectedIconColor = MiniTextSecondary,
                        unselectedTextColor = MiniTextSecondary
                    )
                )
            }
        }
    }
}
