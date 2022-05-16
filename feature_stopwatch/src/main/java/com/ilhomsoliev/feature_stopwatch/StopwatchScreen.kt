package com.ilhomsoliev.feature_stopwatch

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.common_ui.BigButton
import com.ilhomsoliev.common_ui.TimeShower
import kotlin.time.Duration

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun StopwatchScreen(viewModel: StopwatchScreenViewModel = hiltViewModel()) {

    val isPlaying by viewModel.isPlaying

    var sizeState by remember { mutableStateOf(100.dp) }
    val size by animateDpAsState(targetValue = sizeState)

    var cornerSizeState by remember { mutableStateOf(100.dp) }
    val cornerSize by animateDpAsState(targetValue = cornerSizeState)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Stopwatch(
                milliseconds = viewModel.milliseconds,
                seconds = viewModel.seconds,
                minutes = viewModel.minutes,
                hours = viewModel.hours,
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewModel.savedTimes.value.reversed()) {
                Text(text = it, fontFamily = FontFamily.Serif, fontSize = 18.sp)
            }
            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }

    if (isPlaying) {
        sizeState = 120.dp
        cornerSizeState = 100.dp
        cornerSizeState = 20.dp
    } else {
        sizeState = 100.dp
        cornerSizeState = 100.dp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ConstraintLayout {

            val (stop, reset, saveTime) = createRefs()

            BigButton(modifier = Modifier
                .height(100.dp)
                .width(size)
                .clip(RoundedCornerShape(cornerSize))
                .background(Red)
                .clickable {
                    if (isPlaying) {
                        viewModel.onEvent(StopwatchEvent.OnPauseClick)
                    } else {
                        viewModel.onEvent(StopwatchEvent.OnResumeClick)
                    }
                }
                .constrainAs(stop) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, isPlaying
            )

            if (viewModel.time != Duration.ZERO || isPlaying) {
                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color(0xFFED6B6C))

                        .constrainAs(reset) {
                            end.linkTo(stop.start, margin = 48.dp)
                            start.linkTo(parent.start)
                            top.linkTo(stop.top)
                            bottom.linkTo(stop.bottom)
                        },
                    onClick = {
                        viewModel.onEvent(StopwatchEvent.OnResetClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            if (isPlaying) {
                IconButton(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color(0xFFED6B6C))
                        .constrainAs(saveTime) {
                            start.linkTo(stop.end, margin = 48.dp)
                            end.linkTo(parent.end)
                            top.linkTo(stop.top)
                            bottom.linkTo(stop.bottom)
                        },
                    onClick = {
                        viewModel.onEvent(StopwatchEvent.OnSaveTimeClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Stopwatch(
    milliseconds: String,
    seconds: String,
    minutes: String,
    hours: String,
) {
    Row {

        TimeShower(hours = hours, minutes = minutes, seconds = seconds, milliseconds = milliseconds)

    }
}