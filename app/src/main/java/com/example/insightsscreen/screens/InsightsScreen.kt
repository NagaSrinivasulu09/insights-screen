package com.example.insightsscreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.insightsscreen.R
import com.example.insightsscreen.cards.BodyTrendsCard
import com.example.insightsscreen.cards.CorrelationCard
import com.example.insightsscreen.cards.CycleTrendsCard
import com.example.insightsscreen.cards.StabilityCard
import com.example.insightsscreen.cards.SymptomTrendsCard
import com.example.insightsscreen.components.BottomNavBar
import com.example.insightsscreen.components.SectionSpacer
import com.example.insightsscreen.components.SectionTitle
import com.example.insightsscreen.ui.theme.TopGradient

@Composable
fun InsightsScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp) // ✅ space for navbar
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(TopGradient)
            ) {
                Text(
                    text = "Insights",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 44.dp)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_sparkel),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 28.dp, top = 44.dp)
                        .size(26.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {

                SectionTitle("Stability Summary")
                StabilityCard()

                SectionSpacer()

                SectionTitle("Cycle Trends")
                CycleTrendsCard()

                SectionSpacer()

                SectionTitle("Body & Metabolic Trends")
                BodyTrendsCard()

                SectionSpacer()

                SectionTitle("Body Signals")
                SymptomTrendsCard()

                SectionSpacer()

                SectionTitle("Lifestyle Impacts")
                CorrelationCard()
            }
        }
        Box(
            modifier = Modifier
                .padding(start = 32.dp)
                .align(Alignment.BottomCenter)
        ) {
            BottomNavBar()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InsightsScreenPreview() {
    InsightsScreen()

}