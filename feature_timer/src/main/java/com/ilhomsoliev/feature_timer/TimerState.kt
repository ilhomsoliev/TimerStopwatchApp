package com.ilhomsoliev.feature_timer

sealed class TimerState {
    object TimerPickedOnPause:TimerState()
    object TimerPickedOnResume:TimerState()
    object TimerNotPicked:TimerState()
}