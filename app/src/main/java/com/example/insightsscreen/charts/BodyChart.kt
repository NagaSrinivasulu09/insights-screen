package com.example.insightsscreen.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeightChart() {

    val points = listOf(30f, 45f, 35f, 75f, 55f)
    val labels = listOf("Jan", "Feb", "Mar", "Apr", "May")
    val yLabels = listOf("75", "50", "25")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Column(
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            yLabels.forEach {
                Text(it, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Box(
            modifier = Modifier.weight(1f)
        ) {

            Canvas(modifier = Modifier.fillMaxSize()) {

                val w = size.width
                val h = size.height - 24.dp.toPx()

                val max = points.max()
                val min = points.min()

                val stepX = w / (points.size - 1)

                val coords = points.mapIndexed { i, v ->
                    val normalized = (v - min) / (max - min)
                    Offset(i * stepX, h - normalized * h)
                }
                val levels = listOf(25f, 50f, 75f)
                levels.forEach { value ->
                    val normalized = (value - min) / (max - min)
                    val y = h - normalized * h

                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(w, y),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                }
                val path = Path().apply {
                    moveTo(coords.first().x, coords.first().y)
                    for (i in 1 until coords.size) {
                        val prev = coords[i - 1]
                        val curr = coords[i]
                        val cp1 = Offset((prev.x + curr.x) / 2, prev.y)
                        val cp2 = Offset((prev.x + curr.x) / 2, curr.y)
                        cubicTo(cp1.x, cp1.y, cp2.x, cp2.y, curr.x, curr.y)
                    }
                }
                val fillPath = Path().apply {
                    addPath(path)
                    lineTo(coords.last().x, h)
                    lineTo(coords.first().x, h)
                    close()
                }

                drawPath(
                    fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFB3B3), Color.Transparent)
                    )
                )
                drawPath(
                    path,
                    color = Color(0xFFFF6B6B),
                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                )
                coords.forEach {
                    drawCircle(Color.White, radius = 6.dp.toPx(), center = it)
                    drawCircle(Color(0xFFFF6B6B), radius = 3.dp.toPx(), center = it)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                labels.forEach {
                    Text(it, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Preview
@Composable
private fun WeightChartPreview() {
    WeightChart()
}