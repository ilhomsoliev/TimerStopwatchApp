package com.ilhomsoliev.feature_timer

sealed class TimerEvent {
    object OnStartTimer : TimerEvent()
    data class  OnTimePicked(val value:Long) : TimerEvent()
    object OnPauseTimer : TimerEvent()
    object OnResetTimer : TimerEvent()
    object OnResumeTimer:TimerEvent()
}