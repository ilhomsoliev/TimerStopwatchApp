package com.ilhomsoliev.timerandstopwatch.app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ilhomsoliev.feature_stopwatch.StopwatchScreen
import com.ilhomsoliev.feature_timer.TimerScreen
import com.ilhomsoliev.timerandstopwatch.app.presentation.components.BottomBar
import com.ilhomsoliev.timerandstopwatch.app.presentation.components.TopAppBar
import com.ilhomsoliev.timerandstopwatch.core.entities.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            if (currentDestination?.route == Screen.StopwatchScreen.route) {
                TopAppBar(text = "Stopwatch")
            } else {
                TopAppBar(text = "Timer")
            }
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.StopwatchScreen.route,
        ) {
            composable(route = Screen.StopwatchScreen.route) {
                StopwatchScreen()
            }
            composable(route = Screen.TimerScreen.route) {
                TimerScreen()
            }
        }
    }
}