package com.example.insightsscreen.ui.theme
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import kotlin.math.*
/*
// ─── Color Palette ───────────────────────────────────────────────────────────
private val Purple200   = Color(0xFFB8B4E8)
private val Purple400   = Color(0xFF8B84D7)
private val Pink200     = Color(0xFFF4B8C1)
private val Pink400     = Color(0xFFE88A97)
private val Teal300     = Color(0xFF8ECFC9)
private val Green300    = Color(0xFF9ED3A8)
private val BgLight     = Color(0xFFF5F4F8)
private val CardBg      = Color(0xFFFFFFFF)
private val TextPrimary = Color(0xFF1A1A2E)
private val TextSecond  = Color(0xFF8A8A9A)
private val DividerClr  = Color(0xFFE8E8F0)

// ─── Main Screen ─────────────────────────────────────────────────────────────
@Composable
fun InsightsScreen() {
    Scaffold(
        bottomBar = { BottomNavBar() },
        containerColor = BgLight
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TopBar()
            Spacer(Modifier.height(8.dp))
            StabilitySummaryCard()
            Spacer(Modifier.height(16.dp))
            SectionTitle("Cycle Trends")
            CycleTrendsCard()
            Spacer(Modifier.height(16.dp))
            SectionTitle("Body & Metabolic Trends")
            BodyMetabolicCard()
            Spacer(Modifier.height(16.dp))
            SectionTitle("Body Signals")
            BodySignalsCard()
            Spacer(Modifier.height(16.dp))
            SectionTitle("Lifestyle Impact")
            LifestyleImpactCard()
            Spacer(Modifier.height(24.dp))
        }
    }
}

// ─── Top Bar ─────────────────────────────────────────────────────────────────
@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Decorative dot cluster
        Box(modifier = Modifier.size(28.dp)) {
            Box(
                Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Purple200)
                    .align(Alignment.TopStart)
            )
            Box(
                Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Pink200)
                    .align(Alignment.BottomEnd)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            "Insights",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
        )
    }
}

// ─── Section Title ───────────────────────────────────────────────────────────
@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

// ─── Stability Summary Card ──────────────────────────────────────────────────
@Composable
private fun StabilitySummaryCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                "Stability Summary",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Based on your recent logs and symptom patterns.",
                fontSize = 13.sp,
                color = TextSecond
            )
            Spacer(Modifier.height(12.dp))
            Text("Stability Score", fontSize = 12.sp, color = TextSecond)
            Text(
                "78%",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(8.dp))
            StabilityLineChart()
        }
    }
}

@Composable
private fun StabilityLineChart() {
    val months = listOf("Jan", "Feb", "Mar", "Apr")
    // Data points (normalized 0..1) showing upward trend
    val points = listOf(0.35f, 0.42f, 0.68f, 0.72f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height - 24.dp.toPx()
            val xStep = w / (points.size - 1)

            // Compute screen coordinates
            val coords = points.mapIndexed { i, v ->
                Offset(i * xStep, h - v * h * 0.85f)
            }

            // Filled area under curve
            val fillPath = Path().apply {
                moveTo(coords.first().x, h)
                coords.forEach { lineTo(it.x, it.y) }
                lineTo(coords.last().x, h)
                close()
            }
            drawPath(
                fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(Purple200.copy(alpha = 0.45f), Color.Transparent),
                    startY = 0f, endY = h
                )
            )

            // Line
            val linePath = Path().apply {
                moveTo(coords.first().x, coords.first().y)
                for (i in 1 until coords.size) {
                    val cp1 = Offset((coords[i - 1].x + coords[i].x) / 2, coords[i - 1].y)
                    val cp2 = Offset((coords[i - 1].x + coords[i].x) / 2, coords[i].y)
                    cubicTo(cp1.x, cp1.y, cp2.x, cp2.y, coords[i].x, coords[i].y)
                }
            }
            drawPath(linePath, color = Purple400, style = Stroke(width = 2.5f, cap = StrokeCap.Round))

            // Active dot on Mar (index 2)
            drawCircle(color = Teal300, radius = 7f, center = coords[2])
            drawCircle(color = Color.White, radius = 4f, center = coords[2])
        }

        // "Stability Improving" tooltip positioned near Mar point
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-40).dp, y = 10.dp)
                .background(TextPrimary, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                "Stability\nImproving",
                fontSize = 10.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        // Month labels at bottom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            months.forEach { m ->
                Text(
                    m,
                    fontSize = 11.sp,
                    fontWeight = if (m == "Mar") FontWeight.Bold else FontWeight.Normal,
                    color = if (m == "Mar") TextPrimary else TextSecond
                )
            }
        }
    }
}

// ─── Cycle Trends Card ───────────────────────────────────────────────────────
@Composable
private fun CycleTrendsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            val months  = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
            val lengths = listOf(28, 30, 28, 32, 28, 28)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left arrow
                Text("‹", fontSize = 20.sp, color = TextSecond)

                months.forEachIndexed { i, month ->
                    CycleBar(month = month, days = lengths[i])
                }

                // Right arrow
                Text("›", fontSize = 20.sp, color = TextSecond)
            }
        }
    }
}

@Composable
private fun CycleBar(month: String, days: Int) {
    val maxDays = 32
    val totalFraction = days / maxDays.toFloat()
    val barH = 90.dp

    // Segment fractions (ovulation ~30%, period ~20%, follicular ~50%)
    val ovulFrac  = 0.30f
    val periodFrac = 0.20f
    val folFrac   = 0.50f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text("$days", fontSize = 10.sp, color = TextSecond, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(28.dp)
                .height(barH)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFF0EFF7))
        ) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                // Period (pink, bottom)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(barH * totalFraction * periodFrac)
                        .background(Pink300)
                )
                // Ovulation (teal, middle)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(barH * totalFraction * ovulFrac)
                        .background(Teal300)
                )
                // Follicular (purple, top remainder)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(barH * totalFraction * folFrac)
                        .background(Purple200)
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(month, fontSize = 10.sp, color = TextSecond)
    }
}

private val Pink300 = Color(0xFFF4B0BC)

// ─── Body & Metabolic Trends Card ────────────────────────────────────────────
@Composable
private fun BodyMetabolicCard() {
    var selectedTab by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Your weight", fontSize = 13.sp, color = TextSecond)
                    Text("in kg", fontSize = 11.sp, color = TextSecond)
                }
                // Monthly / Weekly toggle
                Box(
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFF0EFF7))
                ) {
                    Row {
                        listOf("Monthly", "Weekly").forEachIndexed { idx, label ->
                            val selected = selectedTab == idx
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (selected) TextPrimary else Color.Transparent)
                                    .clickable { selectedTab = idx }
                                    .padding(horizontal = 12.dp, vertical = 5.dp)
                            ) {
                                Text(
                                    label,
                                    fontSize = 11.sp,
                                    color = if (selected) Color.White else TextSecond
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            WeightLineChart()

            // Y-axis labels
            Row(
                Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Jan", "Feb", "Mar", "Apr", "May").forEach {
                    Text(it, fontSize = 10.sp, color = TextSecond)
                }
            }
        }
    }
}

@Composable
private fun WeightLineChart() {
    // Normalized weight values (peak around Apr)
    val points = listOf(0.30f, 0.45f, 0.62f, 0.82f, 0.55f, 0.60f)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val w = size.width
        val h = size.height
        val xStep = w / (points.size - 1)
        val coords = points.mapIndexed { i, v -> Offset(i * xStep, h - v * h * 0.9f) }

        // Fill area
        val fillPath = Path().apply {
            moveTo(coords.first().x, h)
            coords.forEach { lineTo(it.x, it.y) }
            lineTo(coords.last().x, h)
            close()
        }
        drawPath(fillPath, Brush.verticalGradient(
            listOf(Pink200.copy(alpha = 0.5f), Color.Transparent), 0f, h
        ))

        // Line
        val linePath = Path().apply {
            moveTo(coords.first().x, coords.first().y)
            for (i in 1 until coords.size) {
                val cx = (coords[i - 1].x + coords[i].x) / 2
                cubicTo(cx, coords[i - 1].y, cx, coords[i].y, coords[i].x, coords[i].y)
            }
        }
        drawPath(linePath, Pink400, style = Stroke(2.5f, cap = StrokeCap.Round))

        // Y-axis guide lines
        listOf(0.25f, 0.50f, 0.75f).forEach { frac ->
            val y = h - frac * h
            drawLine(DividerClr, Offset(0f, y), Offset(w, y), strokeWidth = 1f)
        }
    }
}

// ─── Body Signals Card ───────────────────────────────────────────────────────
@Composable
private fun BodySignalsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Symptom Trends", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text("Compared to last cycle", fontSize = 12.sp, color = TextSecond)
            Spacer(Modifier.height(16.dp))
            DonutChart()
        }
    }
}

@Composable
private fun DonutChart() {
    data class Segment(val label: String, val pct: Int, val color: Color, val angle: Float)

    val segments = listOf(
        Segment("Mood",     30, Purple200, 0f),
        Segment("Bloating", 31, Color(0xFFD4B8E8), 108f),
        Segment("Fatigue",  21, Pink300,   220f),
        Segment("Acne",     17, Teal300,   296f),
    )
    val total = segments.sumOf { it.pct }.toFloat()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Canvas(modifier = Modifier.size(180.dp)) {
            val strokeW = 44.dp.toPx()
            val radius  = (size.minDimension - strokeW) / 2
            val center  = Offset(size.width / 2, size.height / 2)
            val rect    = Rect(center - Offset(radius, radius), center + Offset(radius, radius))
            var startAngle = -90f

            segments.forEach { seg ->
                val sweep = (seg.pct / total) * 360f
                drawArc(
                    color      = seg.color,
                    startAngle = startAngle,
                    sweepAngle = sweep - 2f,   // small gap
                    useCenter  = false,
                    style      = Stroke(strokeW, cap = StrokeCap.Round),
                    topLeft    = rect.topLeft,
                    size       = Size(rect.width, rect.height)
                )
                startAngle += sweep
            }
        }

        // Centre hole labels are placed outside via absolute positioning
        // Outer labels
        segments.forEachIndexed { i, seg ->
            val angleDeg = seg.angle - 90f
            val rad = Math.toRadians(angleDeg.toDouble())
            val r = 130.dp
            Box(
                modifier = Modifier
                    .offset(
                        x = (cos(rad) * r.value).dp - 30.dp,
                        y = (sin(rad) * r.value).dp - 14.dp
                    )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "${seg.pct}%",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(seg.label, fontSize = 11.sp, color = TextSecond)
                }
            }
        }
    }
}

// ─── Lifestyle Impact Card ───────────────────────────────────────────────────
@Composable
private fun LifestyleImpactCard() {
    val factors = listOf(
        "Sleep"    to listOf(3, 4, 3, 4, 3, 4, 3, 4) to Purple200,
        "Hydrate"  to listOf(4, 3, 4, 2, 4, 3, 4, 2) to Pink300,
        "Caffeine" to listOf(3, 3, 4, 3, 3, 4, 3, 3) to Teal300,
        "Exercise" to listOf(2, 3, 2, 4, 2, 3, 2, 4) to Pink400,
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Correlation Strength", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, DividerClr, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("4 months", fontSize = 11.sp, color = TextSecond)
                    Text(" ∨", fontSize = 11.sp, color = TextSecond)
                }
            }
            Spacer(Modifier.height(12.dp))

            @Suppress("UNCHECKED_CAST")
            factors.forEach { (pair, color) ->
                val (label, values) = pair
                LifestyleRow(label = label, values = values, color = color)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun LifestyleRow(label: String, values: List<Int>, color: Color) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            label,
            fontSize = 12.sp,
            color = TextSecond,
            modifier = Modifier.width(60.dp)
        )
        Spacer(Modifier.width(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            values.forEach { intensity ->
                Box(
                    modifier = Modifier
                        .size(width = 22.dp, height = 22.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color.copy(alpha = intensity * 0.22f + 0.12f))
                )
            }
        }
    }
}

// ─── Bottom Navigation Bar ───────────────────────────────────────────────────
@Composable
private fun BottomNavBar() {
    val items = listOf(
        NavItem("Home",     "⌂"),
        NavItem("Track",    "◎"),
        NavItem("Insights", "▦"),
    )
    var selected by remember { mutableStateOf(2) }

    Surface(
        color = CardBg,
        shadowElevation = 12.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { idx, item ->
                NavBarItem(
                    item    = item,
                    selected = idx == selected,
                    onClick  = { selected = idx }
                )
            }
            // + button
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(TextPrimary)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Text("+", fontSize = 22.sp, color = Color.White, lineHeight = 22.sp)
            }
        }
    }
}

private data class NavItem(val label: String, val icon: String)

@Composable
private fun NavBarItem(item: NavItem, selected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            item.icon,
            fontSize = 20.sp,
            color = if (selected) TextPrimary else TextSecond
        )
        Text(
            item.label,
            fontSize = 10.sp,
            color = if (selected) TextPrimary else TextSecond,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
        )
    }
}
 */