package com.ilhomsoliev.feature_timer.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.common_ui.BigButton
import com.ilhomsoliev.feature_timer.TimerScreenViewModel

@Composable
fun TimePickerScreen(onStartClick:(String)->Unit) {

    var numberedTime by remember { mutableStateOf("000000") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {

        NumberedTimeLabel(modifier = Modifier.fillMaxWidth(), text = numberedTime)

        Spacer(modifier = Modifier.height(12.dp))

        Numbers(modifier = Modifier.fillMaxWidth(), onNumberClicked = {
            Log.d("Hello", it)
            if (it == "<-") {
                numberedTime = numberedTime.reversed().drop(1).plus("0").reversed()
            } else { // It's Number
                if(it.length == 2){
                    if (numberedTime[0] == '0' && numberedTime[1] == '0') {
                        numberedTime += it
                        numberedTime = numberedTime.drop(2)
                        Log.d("Hello", numberedTime)
                    }
                    if(numberedTime[0] == '0'){
                        numberedTime += it
                        numberedTime = numberedTime.drop(1)
                        Log.d("Hello", numberedTime)
                    }
                }else{
                    if (numberedTime[0] == '0') {
                        //numberedTime.plus(it)
                        numberedTime += it
                        numberedTime = numberedTime.drop(1)
                        Log.d("Hello", numberedTime)
                    }
                }
            }
        })

        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            BigButton(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(1000.dp))
                    .background(Red)
                    .clickable {
                        onStartClick(numberedTime)
                    }, isPlaying = false
            )
        }
    }
}

@Composable
fun Numbers(modifier: Modifier, onNumberClicked: (String) -> Unit) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Number(text = "1", onClick = { onNumberClicked(it) })
            Number(text = "2", onClick = { onNumberClicked(it) })
            Number(text = "3", onClick = { onNumberClicked(it) })
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Number(text = "4", onClick = { onNumberClicked(it) })
            Number(text = "5", onClick = { onNumberClicked(it) })
            Number(text = "6", onClick = { onNumberClicked(it) })
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Number(text = "7", onClick = { onNumberClicked(it) })
            Number(text = "8", onClick = { onNumberClicked(it) })
            Number(text = "9", onClick = { onNumberClicked(it) })
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Number(text = "00", onClick = { onNumberClicked(it) })
            Number(text = "0", onClick = { onNumberClicked(it) })
            Number(text = "<-", onClick = { onNumberClicked(it) })
        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
fun Number(text: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xFF404244))
            .clickable {
                onClick(text)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(modifier = Modifier.padding(4.dp), text = text, color = Color.White, fontSize = 28.sp)
    }
    Spacer(modifier = Modifier.width(12.dp))
}

@Composable
fun NumberedTimeLabel(modifier: Modifier, text: String) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        TimeUnit(timeValue = "${text[0]}${text[1]}", timeUnit = "h")
        TimeUnit(timeValue = "${text[2]}${text[3]}", timeUnit = "m")
        TimeUnit(timeValue = "${text[4]}${text[5]}", timeUnit = "s")
    }
}

@Composable
fun TimeUnit(timeValue: String, timeUnit: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(text = timeValue, fontSize = 60.sp)
        Text(text = timeUnit, fontSize = 18.sp)
    }
}