package com.bakerapps.miniworth.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.R
import com.bakerapps.miniworth.components.CompactProductRow
import com.bakerapps.miniworth.components.ConfidenceBadge
import com.bakerapps.miniworth.data.Product
import com.bakerapps.miniworth.ui.theme.MiniCard
import com.bakerapps.miniworth.ui.theme.MiniCardBorder
import com.bakerapps.miniworth.ui.theme.MiniGold
import com.bakerapps.miniworth.ui.theme.MiniPanel
import com.bakerapps.miniworth.ui.theme.MiniParchment
import com.bakerapps.miniworth.ui.theme.MiniPrimary
import com.bakerapps.miniworth.ui.theme.MiniTextPrimary
import com.bakerapps.miniworth.ui.theme.MiniTextSecondary
import com.bakerapps.miniworth.ui.theme.MedievalSharp

@Composable
fun HomeScreen(
    recentScans: List<Product>,
    onScan: () -> Unit,
    onSearch: () -> Unit,
    onProduct: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        GrimHeader()
        GrimHeroCard()
        GrimActionPanel("Take Photo", "Scan a product, box, mini, or lot", R.drawable.ic_camera_png, MiniPrimary, onScan)
        GrimActionPanel("Upload Screenshot", "Use eBay, Google, or marketplace screenshots", R.drawable.ic_image_upload_png, MiniGold, onScan)
        GrimActionPanel("Scan Barcode", "For boxes, books, and sealed kits", R.drawable.ic_barcode_png, MiniGold, onScan)
        GrimActionPanel("Search Codex", "Lookup units, kits, and datasheets", R.drawable.ic_search_png, MiniPrimary, onSearch)
        ParchmentStrip()

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Recent Scans", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (recentScans.isNotEmpty()) {
                Text("${recentScans.size} in memory", color = MiniPrimary, style = MaterialTheme.typography.labelLarge)
            }
        }
        if (recentScans.isEmpty()) {
            Text(
                "No scans yet this session.",
                color = MiniTextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            recentScans.forEach { product ->
                CompactProductRow(
                    product = product,
                    trailing = { ConfidenceBadge(product.confidence) },
                    onClick = { onProduct(product.id) }
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Unofficial fan-made scanner and value guide. Not affiliated with or endorsed by Games Workshop.",
            color = MiniTextSecondary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
private fun GrimHeader() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MiniCard),
        border = BorderStroke(1.dp, MiniCardBorder),
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .background(Brush.horizontalGradient(listOf(Color(0xFF101418), Color(0xFF0A0C0E), MiniPanel)))
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0xFF11160F), RoundedCornerShape(8.dp))
                    .border(1.dp, MiniPrimary.copy(alpha = 0.78f), RoundedCornerShape(8.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(painterResource(R.drawable.ic_miniworth_box_png), contentDescription = null)
            }
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        "MiniWorth",
                        color = MiniTextPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = MedievalSharp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        " 40K",
                        color = MiniPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = MedievalSharp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Text(
                    "Warhammer 40,000 Mini Scanner & Value Guide",
                    color = MiniGold,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun GrimHeroCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MiniCard),
        border = BorderStroke(1.dp, MiniCardBorder),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(Modifier.fillMaxWidth().height(196.dp)) {
            Image(
                painter = painterResource(R.drawable.miniworth_home_hero),
                contentDescription = "MiniWorth scanner hero",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
            Box(Modifier.matchParentSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.2f)))))
        }
    }
}

@Composable
private fun GrimHeroArt() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(166.dp)
            .background(Color(0xFF0A0C0E))
    ) {
        drawRect(Brush.verticalGradient(listOf(Color(0xFF2B2E2D), Color(0xFF111315), Color(0xFF070809))))
        repeat(9) { index ->
            val x = size.width * (index / 8f)
            drawLine(Color(0x552E352F), Offset(x, size.height * 0.2f), Offset(x - 80f, size.height * 0.82f), strokeWidth = 3f)
        }
        drawCircle(Color(0x553F7C38), radius = size.width * 0.16f, center = Offset(size.width * 0.52f, size.height * 0.34f))
        drawSoldier(Offset(size.width * 0.50f, size.height * 0.45f), 1.2f)
        drawSoldier(Offset(size.width * 0.28f, size.height * 0.54f), 0.78f)
        drawSoldier(Offset(size.width * 0.72f, size.height * 0.55f), 0.76f)
        drawRect(Color.Black.copy(alpha = 0.3f), topLeft = Offset(0f, size.height * 0.76f), size = Size(size.width, size.height * 0.24f))
        drawRect(Color(0x883D434C), style = Stroke(width = 2f))
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawSoldier(center: Offset, scale: Float) {
    val armor = Color(0xFF252A2F)
    val highlight = Color(0xFF46505A)
    val green = Color(0xFF76D64B)
    drawCircle(armor, radius = 22f * scale, center = center.copy(y = center.y - 42f * scale))
    drawCircle(green, radius = 3.5f * scale, center = center.copy(x = center.x + 7f * scale, y = center.y - 46f * scale))
    drawOval(armor, topLeft = Offset(center.x - 34f * scale, center.y - 24f * scale), size = Size(68f * scale, 74f * scale))
    drawCircle(highlight, radius = 17f * scale, center = center.copy(x = center.x - 37f * scale, y = center.y - 8f * scale))
    drawCircle(highlight, radius = 17f * scale, center = center.copy(x = center.x + 37f * scale, y = center.y - 8f * scale))
    drawLine(Color(0xFF171A1D), Offset(center.x - 68f * scale, center.y + 2f * scale), Offset(center.x + 70f * scale, center.y + 32f * scale), strokeWidth = 12f * scale)
    drawLine(Color(0xFFB08A3E), Offset(center.x - 66f * scale, center.y - 2f * scale), Offset(center.x + 70f * scale, center.y + 28f * scale), strokeWidth = 2f * scale)
    drawRect(Color(0xFF111315), topLeft = Offset(center.x - 19f * scale, center.y + 45f * scale), size = Size(14f * scale, 42f * scale))
    drawRect(Color(0xFF111315), topLeft = Offset(center.x + 7f * scale, center.y + 45f * scale), size = Size(14f * scale, 42f * scale))
}

@Composable
private fun GrimActionPanel(
    title: String,
    subtitle: String,
    iconRes: Int,
    tint: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(58.dp).clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MiniCard),
        border = BorderStroke(1.dp, MiniCardBorder),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(listOf(Color(0xFF11161B), Color(0xFF0B0D0F), MiniPanel)))
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(0xFF0A0C0E), RoundedCornerShape(6.dp))
                    .border(1.dp, tint.copy(alpha = 0.82f), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(31.dp)
                )
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(title.uppercase(), color = MiniTextPrimary, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Black)
                Text(subtitle, color = MiniTextSecondary, style = MaterialTheme.typography.bodySmall)
            }
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null, tint = MiniPrimary)
        }
    }
}

@Composable
private fun ParchmentStrip() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MiniParchment),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MiniGold.copy(alpha = 0.65f))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Brush.horizontalGradient(listOf(MiniParchment, Color(0xFFC9B98F))))
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(Icons.Rounded.Shield, contentDescription = null, tint = Color(0xFF1A2026), modifier = Modifier.size(28.dp))
            Column {
                Text("SERVE THE HOBBY. KNOWLEDGE IS POWER.", color = Color(0xFF14100A), style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Black)
                Text("Build your collection. Track your finds. Honor the craft.", color = Color(0xFF312816), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
