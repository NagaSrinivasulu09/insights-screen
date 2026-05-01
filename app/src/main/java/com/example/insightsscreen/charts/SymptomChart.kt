package com.example.insightsscreen.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.insightsscreen.data.SymptomData

@Composable
fun SymptomChart(data: List<SymptomData>,modifier: Modifier) {

    Box(
        modifier = Modifier
            .size(220.dp),
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {

            val total = data.sumOf { it.percentage.toDouble() }.toFloat()

            var startAngle = -90f

            val strokeWidth = 28.dp.toPx()

            data.forEach { item ->

                val sweep = (item.percentage / total) * 360f

                drawArc(
                    color = item.color,
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )

                startAngle += sweep
            }
        }
    }
}

@Preview
@Composable
private fun SymptomChartPreview() {
    val data = listOf(
        SymptomData("Mood", 30f, Color(0xFFEFB6B6)),
        SymptomData("Bloating", 31f, Color(0xFFB9A7FF)),
        SymptomData("Fatigue", 21f, Color(0xFFFF9E9E)),
        SymptomData("Acne", 17f, Color(0xFF9FE3D0))
    )
    SymptomChart(data, modifier = Modifier)
}