package com.ilhomsoliev.common_ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TimeShower(
    hours: String,
    minutes: String,
    seconds: String,
    milliseconds: String,
    showMilliseconds: Boolean = true
) {
    Row {
        val numberTransitionSpec: AnimatedContentScope<String>.() -> ContentTransform = {
            slideInVertically(
                initialOffsetY = { it }
            ) + fadeIn() with slideOutVertically(
                targetOffsetY = { -it }
            ) + fadeOut() using SizeTransform(
                false
            )
        }

        CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.h3) {
            if (hours != "00") {
                AnimatedContent(targetState = hours, transitionSpec = numberTransitionSpec) {
                    Text(text = it)
                }
                Text(text = ":")
            }
            if (minutes != "00" || (hours != "00")) {
                AnimatedContent(targetState = minutes, transitionSpec = numberTransitionSpec) {
                    Text(text = it)
                }
                Text(text = ":")
            }
            AnimatedContent(targetState = seconds, transitionSpec = numberTransitionSpec) {
                Text(text = it)
            }
            if (showMilliseconds) {
                Text(text = ":")

                Text(text = milliseconds)
            }
        }
    }
}