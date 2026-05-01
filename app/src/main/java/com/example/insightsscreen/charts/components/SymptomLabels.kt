package com.example.insightsscreen.charts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insightsscreen.data.SymptomData
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SymptomLabels(data: List<SymptomData>,modifier: Modifier) {

    val size = 220.dp
    val radius = 80f

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {

        val total = data.sumOf { it.percentage.toDouble() }.toFloat()
        var startAngle = -90f

        data.forEach { item ->

            val sweep = (item.percentage / total) * 360f
            val midAngle = startAngle + sweep / 2

            val angleRad = Math.toRadians(midAngle.toDouble())

            val x = cos(angleRad).toFloat()
            val y = sin(angleRad).toFloat()
            val offsetX = (radius * x).dp
            val offsetY = (radius * y).dp

            Box(
                modifier = Modifier
                    .offset(x = offsetX, y = offsetY)
                    .background(Color.White, RoundedCornerShape(50))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "${item.percentage.toInt()}%",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        item.label,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                }
            }

            startAngle += sweep
        }
    }
}