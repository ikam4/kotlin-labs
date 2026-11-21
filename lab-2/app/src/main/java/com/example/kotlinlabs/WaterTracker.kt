package com.example.kotlinlabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val GlassMilliliters = 250
private const val DailyGoalMilliliters = 1500
private val WaterAccent = Color(0xFF39588B)
private val WaterAmountColor = Color(0xFF4B5565)
private val WaterBackground = Color(0xFFF7F7F7)

@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    var waterCount by rememberSaveable { mutableIntStateOf(100) }
    var consecutiveDays by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.water_tracker_title),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = WaterAccent,
        )
        Spacer(Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.water_amount, waterCount),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = WaterAmountColor,
        )
        Button(
            onClick = { waterCount += GlassMilliliters },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WaterAccent),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        ) {
            Text(stringResource(R.string.add_water), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = {
                consecutiveDays = if (waterCount >= DailyGoalMilliliters) consecutiveDays + 1 else 0
                waterCount = 0
            },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = WaterAccent),
        ) {
            Text(stringResource(R.string.finish_day))
        }
        Text(stringResource(R.string.water_streak, consecutiveDays), textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun WaterTrackerPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(WaterBackground),
            contentAlignment = Alignment.Center,
        ) {
            WaterTracker()
        }
    }
}
