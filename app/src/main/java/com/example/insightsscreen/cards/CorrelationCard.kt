package com.example.insightsscreen.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insightsscreen.charts.components.CorrelationGrid
import com.example.insightsscreen.data.CorrelationData

@Composable
fun CorrelationCard() {
    val data = listOf(
        CorrelationData(
            "Sleep",
            listOf(0.3f, 0.4f, 0.5f, 0.6f, 0.5f, 0.4f, 0.3f, 0f),
            Color(0xFFC2B8F0)
        ),
        CorrelationData(
            "Hydrate",
            listOf(0.5f, 0.6f, 0.7f, 0f, 0f, 0f, 0f, 0f),
            Color(0xFFEFAEAF)
        ),
        CorrelationData(
            "Caffeine",
            listOf(0.6f, 0.5f, 0.7f, 0.6f, 0.7f, 0f, 0f, 0f),
            Color(0xFFA5D4C9)
        ),
        CorrelationData(
            "Exercise",
            listOf(0.4f, 0.5f, 0.6f, 0.5f, 0f, 0f, 0f, 0f),
            Color(0xFFEFBABA)

        )
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9FB)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Correlation Strength",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    "4 months",
                    modifier = Modifier
                        .background(Color(0xFFF1F1F1), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CorrelationGrid(data)
        }
    }
}

@Preview
@Composable
private fun CorrelationPreview() {
    CorrelationCard()
}