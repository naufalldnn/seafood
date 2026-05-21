package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // NEW CONCEPT: Pearl & Champagne (Luxury, Bright, No Black)
        val luxuryOceanScheme = lightColorScheme(
            primary = Color(0xFFC5A059), // Champagne Gold
            onPrimary = Color.White,
            primaryContainer = Color(0xFFF3E5AB), // Vanilla Pearl
            onPrimaryContainer = Color(0xFF5D4037), // Soft Brown (for contrast)
            secondary = Color(0xFF0077BE), // Mediterranean Blue
            onSecondary = Color.White,
            background = Color(0xFFFAFAFA), // Off-white
            surface = Color.White,
            onSurface = Color(0xFF4E342E), // Rich Espresso (Deep but not black)
            surfaceVariant = Color(0xFFF5F5F0) // Subtle Sand
        )

        setContent {
            MaterialTheme(colorScheme = luxuryOceanScheme) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    bottomBar = {
                        if (currentDestination?.route in listOf("home", "menu", "profile")) {
                            NavigationBar(
                                containerColor = Color.White,
                                tonalElevation = 12.dp
                            ) {
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                                    label = { Text("Home") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                                    onClick = {
                                        navController.navigate("home") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Menu, contentDescription = null) },
                                    label = { Text("Menu") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "menu" } == true,
                                    onClick = {
                                        navController.navigate("menu") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                                NavigationBarItem(
                                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                                    label = { Text("Profile") },
                                    selected = currentDestination?.hierarchy?.any { it.route == "profile" } == true,
                                    onClick = {
                                        navController.navigate("profile") {
                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.background)
                            .luxuryTexture(),
                        color = Color.Transparent
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") { HomeScreen(navController) }
                            composable("menu") { MenuScreen(navController) }
                            composable(
                                "detail/{menuId}",
                                arguments = listOf(navArgument("menuId") { type = androidx.navigation.NavType.StringType })
                            ) { backStackEntry ->
                                val menuId = backStackEntry.arguments?.getString("menuId") ?: ""
                                DetailMenuScreen(navController, menuId)
                            }
                            composable("profile") { ProfileScreen(navController) }
                            composable("edit_profile") { EditProfileScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Adds a soft, luxury grain/shimmer effect
 */
fun Modifier.luxuryTexture(): Modifier = this.drawBehind {
    val goldAlpha = 0.04f
    // Draw very subtle diagonal gold lines
    val spacing = 40.dp.toPx()
    var i = -size.height
    while (i < size.width) {
        drawLine(
            color = Color(0xFFC5A059).copy(alpha = goldAlpha),
            start = Offset(i, 0f),
            end = Offset(i + size.height, size.height),
            strokeWidth = 1.dp.toPx()
        )
        i += spacing
    }
}
