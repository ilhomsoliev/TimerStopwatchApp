package com.ilhomsoliev.feature_stopwatch

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

@HiltViewModel
class StopwatchScreenViewModel @Inject constructor() : ViewModel() {
    var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var milliseconds by mutableStateOf("00")
    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")

    private var _isPlaying = mutableStateOf(false)
    var isPlaying: State<Boolean> = _isPlaying

    private var _savedTimes = mutableStateOf(mutableListOf<String>())
    var savedTimes: State<List<String>> = _savedTimes

    @OptIn(ExperimentalTime::class)
    fun start() {
        timer = fixedRateTimer(initialDelay = 1L, period = 1L) {
            time = time.plus(1.milliseconds)
            updateTimeStates()
        }
        _isPlaying.value = true
    }

    private fun updateTimeStates() {
        time.toComponents { hours: Long, minutes: Int, seconds: Int, milliseconds: Int ->
            this.milliseconds = (milliseconds / 10000000).pad()
            this.seconds = seconds.pad()
            this.minutes = minutes.pad()
            this.hours = hours.toInt().pad()
        }
    }

    private fun Int.pad(): String {
        return this.toString().padStart(2, '0')
    }

    fun pause() {
        timer.cancel()
        _isPlaying.value = false
    }

    fun stop() {
        pause()
        time = Duration.ZERO
        updateTimeStates()
    }

    private fun saveTime() {
        time.toComponents { hours: Long, minutes: Int, seconds: Int, milliseconds: Int ->
            val curMilliseconds = (milliseconds / 10000000).pad()
            val curSeconds = seconds.pad()
            val curMinutes = minutes.pad()
            val curHours = hours.toInt().pad()

            if (_savedTimes.value.size == 0) {
                _savedTimes.value.add("#${(_savedTimes.value.size + 1).pad()} $curMinutes $curSeconds.$curMilliseconds")
            } else {
                _savedTimes.value.add("#${(_savedTimes.value.size + 1).pad()} $curMinutes $curSeconds.$curMilliseconds")
            }
        }
    }

    fun onEvent(event: StopwatchEvent) {
        when (event) {
            is StopwatchEvent.OnResumeClick -> {
                start()
            }
            is StopwatchEvent.OnPauseClick -> {
                pause()
            }
            is StopwatchEvent.OnResetClick -> {
                stop()
                _savedTimes.value = mutableListOf()
            }
            is StopwatchEvent.OnSaveTimeClick -> {
                saveTime()
            }
        }
    }
}