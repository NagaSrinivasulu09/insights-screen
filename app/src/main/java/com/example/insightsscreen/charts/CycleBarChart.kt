package com.example.insightsscreen.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insightsscreen.data.CycleTrendsData

@Composable
fun CycleBarChart(data: List<CycleTrendsData>) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {

        data.forEach { item ->
            val maxValue = data.maxOf { it.value }
            val maxBarHeight = 220.dp
            val barHeightRatio = item.value / maxValue.toFloat()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔢 Value on top
                Text(
                    text = item.value.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(6.dp))

                // 📊 Bar
                Box(
                    modifier = Modifier
                        .height(maxBarHeight * barHeightRatio)
                        .width(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFEDE7FF)) // base
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        val colors = listOf(
                            Color(0xFFFFA6A6), // pink
                            Color(0xFF7ED1B2), // green
                            MaterialTheme.colorScheme.primary // purple
                        )

                        item.segments.forEachIndexed { index, fraction ->

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(fraction)
                                    .background(colors[index])
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // 📅 Month
                Text(
                    text = item.month,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun CycleBarChartPreview() {
    val sampleData = listOf(
        CycleTrendsData("Jan", 28, listOf(0.3f, 0.3f, 0.4f)),
       CycleTrendsData("Feb", 30, listOf(0.25f, 0.35f, 0.4f)),
       CycleTrendsData("Mar", 28, listOf(0.3f, 0.3f, 0.4f)),
       CycleTrendsData("Apr", 32, listOf(0.2f, 0.4f, 0.4f)),
       CycleTrendsData("May", 28, listOf(0.3f, 0.3f, 0.4f)),
       CycleTrendsData("Jun", 28, listOf(0.35f, 0.25f, 0.4f))
    )
    CycleBarChart(data = sampleData)
}