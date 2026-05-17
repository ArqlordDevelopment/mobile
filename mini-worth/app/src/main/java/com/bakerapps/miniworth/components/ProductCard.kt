package com.bakerapps.miniworth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.data.Product
import com.bakerapps.miniworth.ui.theme.MiniCard
import com.bakerapps.miniworth.ui.theme.MiniCardBorder
import com.bakerapps.miniworth.ui.theme.MiniGold
import com.bakerapps.miniworth.ui.theme.MiniPanel
import com.bakerapps.miniworth.ui.theme.MiniPrimary
import com.bakerapps.miniworth.ui.theme.MiniTextSecondary

@Composable
fun ProductImagePlaceholder(
    modifier: Modifier = Modifier,
    accent: Color = MiniPrimary
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.linearGradient(
                listOf(Color(0xFF19222A), Color(0xFF0B0F13), accent.copy(alpha = 0.18f))
                    .map { it.copy(alpha = 0.95f) }
            )
            )
    ) {
        Canvas(Modifier.matchParentSize()) {
            val baseY = size.height * 0.78f
            drawRect(Color.Black.copy(alpha = 0.28f), topLeft = Offset(0f, baseY), size = Size(size.width, size.height - baseY))
            drawCircle(accent.copy(alpha = 0.35f), radius = size.minDimension * 0.18f, center = Offset(size.width * 0.28f, size.height * 0.45f))
            drawRect(MiniGold.copy(alpha = 0.45f), topLeft = Offset(size.width * 0.48f, size.height * 0.34f), size = Size(size.width * 0.24f, size.height * 0.32f))
            drawLine(accent, Offset(size.width * 0.16f, baseY), Offset(size.width * 0.84f, baseY), strokeWidth = 3.dp.toPx())
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MiniCard),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MiniCardBorder)
    ) {
        Column(Modifier.padding(10.dp)) {
            Box {
                ProductImagePlaceholder(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.55f)
                )
                Icon(
                    Icons.Rounded.MoreVert,
                    contentDescription = "More options",
                    tint = MiniTextSecondary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(22.dp)
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                product.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(product.faction, color = MiniTextSecondary, style = MaterialTheme.typography.bodySmall)
            Text(product.category, color = MiniPrimary, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun CompactProductRow(
    product: Product,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(MiniPanel, MiniCard)))
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProductImagePlaceholder(Modifier.size(width = 84.dp, height = 58.dp))
        Column(Modifier.weight(1f)) {
            Text(product.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(product.faction, color = MiniTextSecondary, style = MaterialTheme.typography.bodySmall)
            Text(product.gameSystem, color = MiniTextSecondary, style = MaterialTheme.typography.bodySmall)
        }
        trailing?.invoke()
    }
}
