package com.example.insightsscreen.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.insightsscreen.charts.CycleBarChart
import com.example.insightsscreen.data.CycleTrendsData

@Composable
fun CycleTrendsCard() {
    val data = listOf(
        CycleTrendsData("Jan", 28, listOf(0.3f, 0.3f, 0.4f)),
        CycleTrendsData("Feb", 30, listOf(0.25f, 0.35f, 0.4f)),
        CycleTrendsData("Mar", 28, listOf(0.3f, 0.3f, 0.4f)),
        CycleTrendsData("Apr", 32, listOf(0.2f, 0.4f, 0.4f)),
        CycleTrendsData("May", 28, listOf(0.3f, 0.3f, 0.4f)),
        CycleTrendsData("Jun", 28, listOf(0.35f, 0.25f, 0.4f))
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        CycleBarChart(data)
    }
}
