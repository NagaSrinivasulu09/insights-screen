package com.example.insightsscreen.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(

    // Small components (chips, small buttons)
    small = RoundedCornerShape(8.dp),

    // Medium components (cards, list items)
    medium = RoundedCornerShape(16.dp),

    // Large components (bottom sheets, dialogs, big cards)
    large = RoundedCornerShape(24.dp)
)