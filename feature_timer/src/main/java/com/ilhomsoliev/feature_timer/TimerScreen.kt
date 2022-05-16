package com.ilhomsoliev.feature_timer

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.feature_timer.components.ActiveTimerScreen
import com.ilhomsoliev.feature_timer.components.TimePickerScreen

@Composable
fun TimerScreen(viewModel: TimerScreenViewModel = hiltViewModel()) {

    val isTimerPicked by viewModel.isTimerPicked
    val isTimerRunning by viewModel.isTimerRunning

    Crossfade(targetState = isTimerPicked) { scene ->
        when (scene) {
            true -> {
                ActiveTimerScreen(
                    totalTime = viewModel.timerValue.value, isTimerRunning = isTimerRunning,
                    onTimerRunningEnable = {
                        viewModel.setIsTimerRunning(it)
                    },
                    onPauseClick = {
                        viewModel.onEvent(TimerEvent.OnPauseTimer)
                    },
                    onResumeClick = {
                        viewModel.onEvent(TimerEvent.OnResumeTimer)
                    },
                    onResetClick = {
                        viewModel.onEvent(TimerEvent.OnResetTimer)
                    },
                )
            }
            false -> {
                TimePickerScreen(onStartClick = { numberedTime ->
                    if (numberedTime != "000000") {
                        val hours = "${numberedTime[0]}${numberedTime[1]}".toInt()
                        val minutes = "${numberedTime[2]}${numberedTime[3]}".toInt()
                        var seconds = "${numberedTime[4]}${numberedTime[5]}".toLong()
                        seconds += minutes * 60
                        seconds += hours * 3600
                        viewModel.onEvent(TimerEvent.OnTimePicked(seconds * 1000))
                    }
                })
            }
        }
    }

}

