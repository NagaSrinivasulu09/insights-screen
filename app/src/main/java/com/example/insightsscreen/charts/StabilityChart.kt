package com.example.insightsscreen.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StabilityChart(modifier: Modifier) {
    val points = listOf(0.2f, 0.35f, 0.5f, 0.65f)

    val primary = MaterialTheme.colorScheme.primary
    val light = primary.copy(alpha = 0.2f)
    val medium = primary.copy(alpha = 0.4f)
    val dark = primary.copy(alpha = 0.7f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {

            val w = size.width
            val h = size.height
            val stepX = w / (points.size - 1)

            fun createLayer(offset: Float): Path {
                val path = Path()

                points.forEachIndexed { i, v ->
                    val x = i * stepX
                    val y = h - ((v + offset) * h)

                    if (i == 0) path.moveTo(x, y)
                    else path.lineTo(x, y)
                }
                path.lineTo(w, h)
                path.lineTo(0f, h)
                path.close()

                return path
            }
            drawPath(
                path = createLayer(0.15f),
                color = light
            )
            drawPath(
                path = createLayer(0.08f),
                color = medium
            )
            drawPath(
                path = createLayer(0f),
                color = dark
            )
        }
    }
}


@Preview
@Composable
private fun StabilityChartPreview() {
    StabilityChart(modifier = Modifier)
}