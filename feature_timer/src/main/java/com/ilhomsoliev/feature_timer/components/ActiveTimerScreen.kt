package com.ilhomsoliev.feature_timer.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ilhomsoliev.common_ui.BigButton
import com.ilhomsoliev.common_ui.theme.DarkRed
import com.ilhomsoliev.feature_stopwatch.StopwatchEvent

@Composable
fun ActiveTimerScreen(
    totalTime: Long,
    isTimerRunning: Boolean,
    onTimerRunningEnable: (Boolean) -> Unit,
    onPauseClick: () -> Unit,
    onResumeClick: () -> Unit,
    onResetClick: () -> Unit

) {

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
            Timer(
                totalTime = totalTime,
                handleColor = DarkRed,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Red,
                modifier = Modifier.size(300.dp),
                isTimerRunning = isTimerRunning,
                /*onTimerRunningEnable = onTimerRunningEnable*/
            )

        }
    }

    if (isTimerRunning) {
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
                .background(Color.Red)
                .clickable {
                    if (isTimerRunning) {
                        onPauseClick()
                        //viewModel.onEvent(StopwatchEvent.OnPauseClick)
                    } else {
                        onResumeClick()
                        //viewModel.onEvent(StopwatchEvent.OnResumeClick)
                    }
                }
                .constrainAs(stop) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, isTimerRunning
            )

            if (totalTime != 0L || isTimerRunning) {
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
                        onResetClick()
                        //viewModel.onEvent(StopwatchEvent.OnResetClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            /* if (isTimerRunning) {
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
                     }
                 ) {
                     Icon(
                         imageVector = Icons.Default.Timer,
                         contentDescription = null,
                         tint = Color.White
                     )
                 }
             }*/
        }
    }
}