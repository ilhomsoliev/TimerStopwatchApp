package com.ilhomsoliev.feature_stopwatch

sealed class StopwatchEvent {
    object OnResumeClick : StopwatchEvent()
    object OnPauseClick : StopwatchEvent()
    object OnResetClick : StopwatchEvent()
    object OnSaveTimeClick : StopwatchEvent()
}