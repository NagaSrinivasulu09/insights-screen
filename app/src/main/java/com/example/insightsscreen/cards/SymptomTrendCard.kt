package com.example.insightsscreen.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insightsscreen.charts.SymptomChart
import com.example.insightsscreen.charts.components.SymptomLabels
import com.example.insightsscreen.data.SymptomData

@Composable
fun SymptomTrendsCard() {

    val data = listOf(
        SymptomData("Mood", 30f, Color(0xFFEFB6B6)),
        SymptomData("Bloating", 31f, Color(0xFFB9A7FF)),
        SymptomData("Fatigue", 21f, Color(0xFFFF9E9E)),
        SymptomData("Acne", 17f, Color(0xFF9FE3D0))
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text("Symptom Trends", fontWeight = FontWeight.SemiBold)
            Text("Compared to last cycle", fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentAlignment = Alignment.Center
            ) {

                SymptomChart(data, modifier = Modifier.size(220.dp))
                SymptomLabels(data, modifier = Modifier.size(220.dp))
            }
        }
    }
}

@Preview
@Composable
private fun SymptomTrendCardPreview() {
    SymptomTrendsCard()
}