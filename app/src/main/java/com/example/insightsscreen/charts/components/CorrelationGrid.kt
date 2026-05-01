package com.example.insightsscreen.charts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insightsscreen.data.CorrelationData

@Composable
fun  CorrelationGrid(data : List<CorrelationData>, modifier: Modifier = Modifier) {

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            data.forEach { row ->

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = row.label, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(70.dp),
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {

                        row.values.forEach { value ->

                            val color = if (value == 0f) {
                                Color(0xFFEAEAF0)
                            } else {
                                row.baseColor.copy(alpha = 0.4f + (value * 0.7f))
                            }

                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(color)
                            )
                        }
                    }
                }
            }
        }
    }
