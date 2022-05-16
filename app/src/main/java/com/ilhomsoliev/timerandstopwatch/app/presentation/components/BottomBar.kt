package com.ilhomsoliev.timerandstopwatch.app.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import com.ilhomsoliev.timerandstopwatch.app.model.BottomNavDestination
import com.ilhomsoliev.common_ui.theme.Red
import com.ilhomsoliev.timerandstopwatch.core.entities.Screen

@Composable
fun BottomBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = White.copy(alpha = 0.95F),
        contentColor = Red
    ) {

        bottomNavDestinations.forEach { navItem ->
            BottomNavItem(navController = navController, item = navItem)
        }

    }
}

val bottomNavDestinations = listOf(
    BottomNavDestination(
        label = "Stopwatch",
        destinationRoute = Screen.StopwatchScreen.route,
        icon = Icons.Default.Timer
    ),
    BottomNavDestination(
        label = "Timer",
        destinationRoute = Screen.TimerScreen.route,
        icon = Icons.Default.HourglassEmpty
    ),
)