package com.example.insightsscreen.data

import androidx.compose.ui.graphics.Color

data class CorrelationData(
    val label: String,
    val values: List<Float>,
    val baseColor: Color
)
