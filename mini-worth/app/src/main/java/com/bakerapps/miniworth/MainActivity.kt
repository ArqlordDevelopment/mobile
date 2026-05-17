package com.bakerapps.miniworth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bakerapps.miniworth.navigation.AppNavigation
import com.bakerapps.miniworth.ui.theme.MiniWorthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniWorthTheme {
                AppNavigation()
            }
        }
    }
}
