package com.ilhomsoliev.feature_timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerScreenViewModel @Inject constructor() : ViewModel(

) {

    private val _isTimerPicked = mutableStateOf<Boolean>(false)
    val isTimerPicked: State<Boolean> = _isTimerPicked

    private val _isTimerRunning = mutableStateOf<Boolean>(false)
    val isTimerRunning: State<Boolean> = _isTimerRunning

    private val _timerValue = mutableStateOf<Long>(0L)
    val timerValue: State<Long> = _timerValue

    fun onEvent(event: TimerEvent) {
        when (event) {
            is TimerEvent.OnTimePicked -> {
                _timerValue.value = event.value
                _isTimerPicked.value = true
                _isTimerRunning.value = true
                onEvent(TimerEvent.OnStartTimer)
            }
            is TimerEvent.OnStartTimer -> {
                _isTimerRunning.value = true
            }
            is TimerEvent.OnPauseTimer -> {
                _isTimerRunning.value = false
            }
            is TimerEvent.OnResetTimer -> {
                _isTimerPicked.value = false
            }
            is TimerEvent.OnResumeTimer -> {
                _isTimerRunning.value = true
            }
        }
    }

    fun setIsTimerRunning(value: Boolean) {
        _isTimerRunning.value = value
    }

}