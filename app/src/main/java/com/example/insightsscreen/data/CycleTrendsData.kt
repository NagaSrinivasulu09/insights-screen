package com.example.insightsscreen.data

data class CycleTrendsData(
    val month: String,
    val value: Int,
    val segments: List<Float>
)