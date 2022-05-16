package com.ilhomsoliev.timerandstopwatch.core.entities

sealed class Screen(val route: String) {
    object StopwatchScreen : Screen("StopwatchScreen")
    object TimerScreen : Screen("TimerScreen")


}