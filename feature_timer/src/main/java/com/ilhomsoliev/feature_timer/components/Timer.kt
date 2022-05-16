package com.ilhomsoliev.feature_timer.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.common_ui.TimeShower
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp,
    isTimerRunning: Boolean,
    /*onTimerRunningEnable: (Boolean) -> Unit*/
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Log.d(
            "Hello Timer Hours",
            if ((currentTime / 1000 / 3600).toString().length == 1) "0" else "" + (currentTime / 1000 / 3600).toString()
        )
        Log.d(
            "Hello Timer Minues",
            if ((currentTime / 1000 / 60 % 60).toString().length == 1) "0" else "" + ((currentTime / 1000 / 60) % 60).toString()
        )
        Log.d(
            "Hello Timer Seconds",
            if ((currentTime / 1000 % 60).toString().length == 1) "0" else "" + ((currentTime / 1000) % 60).toString()
        )
        Log.d(
            "Hello Timer Millics",
            if ((currentTime % 100).toString().length == 1) "0" else "" + (currentTime % 100).toString()
        )
        TimeShower(
            hours = if ((currentTime / 1000 / 3600).toString().length == 1) "0${(currentTime / 1000 / 3600)}" else (currentTime / 1000 / 3600).toString(),
            minutes = if ((currentTime / 1000 / 60 % 60).toString().length == 1) "0${((currentTime / 1000 / 60) % 60).toString()}" else ((currentTime / 1000 / 60) % 60).toString(),
            seconds = if ((currentTime / 1000 % 60).toString().length == 1) "0${((currentTime / 1000) % 60).toString()}" else ((currentTime / 1000) % 60).toString(),
            milliseconds = if ((currentTime % 100).toString().length == 1) "0${(currentTime % 100).toString()}" else (currentTime % 100).toString(),
            showMilliseconds = false
        )

        /*Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    onTimerRunningEnable(true)
                } else {
                    onTimerRunningEnable(!isTimerRunning)
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
        ) {
            Text(
                text = if (isTimerRunning && currentTime >= 0L) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Restart"
            )
        }*/
    }
}