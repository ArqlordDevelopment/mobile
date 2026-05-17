package com.bakerapps.miniworth.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FlashOn
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.components.ProductImagePlaceholder
import com.bakerapps.miniworth.ui.theme.MiniBackground
import com.bakerapps.miniworth.ui.theme.MiniCard
import com.bakerapps.miniworth.ui.theme.MiniPrimary
import com.bakerapps.miniworth.ui.theme.MiniTextSecondary

@Composable
fun ScanScreen(onBack: () -> Unit, onResult: () -> Unit) {
    var mode by remember { mutableStateOf("PHOTO") }
    Column(Modifier.fillMaxSize().background(MiniBackground)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) { Icon(Icons.Rounded.Close, contentDescription = "Close") }
            Text("Scan Item", modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            IconButton(onClick = { }) { Icon(Icons.Rounded.FlashOn, contentDescription = "Flash placeholder") }
        }

        Box(Modifier.weight(1f).fillMaxWidth().padding(horizontal = 16.dp).clip(RoundedCornerShape(24.dp)).background(Color(0xFF0A0D10))) {
            // TODO: Add CameraX camera capture here after the mock flow is approved.
            // TODO: Add Gemini/OpenAI image recognition once backend scanning is ready.
            ProductImagePlaceholder(Modifier.align(Alignment.Center).size(width = 220.dp, height = 150.dp))
            ScannerCorners(Modifier.align(Alignment.Center).size(280.dp))
            Text(
                "Center the item in the frame",
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 28.dp).clip(RoundedCornerShape(50)).background(Color.Black.copy(alpha = 0.62f)).padding(horizontal = 16.dp, vertical = 8.dp),
                color = MiniTextSecondary,
                style = MaterialTheme.typography.labelLarge
            )
        }

        Row(
            Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RoundToolButton(Icons.Rounded.Image, "Gallery", onResult)
            Box(
                modifier = Modifier.size(82.dp).clip(CircleShape).background(Color.White).border(7.dp, MiniPrimary, CircleShape).clickable(onClick = onResult)
            )
            RoundToolButton(Icons.Rounded.QrCodeScanner, "Barcode", onResult)
        }

        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("PHOTO", "BARCODE", "SCREENSHOT").forEach { item ->
                FilterChip(
                    selected = mode == item,
                    onClick = {
                        mode = item
                        if (item == "BARCODE") {
                            // TODO: Add ML Kit barcode scanning for on-device barcode reads.
                        }
                    },
                    label = { Text(item) },
                    colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MiniPrimary, selectedLabelColor = MiniBackground)
                )
            }
        }
    }
}

@Composable
private fun ScannerCorners(modifier: Modifier = Modifier) {
    Canvas(modifier) {
        val l = 34.dp.toPx()
        val s = 4.dp.toPx()
        fun corner(a: Offset, b: Offset) = drawLine(MiniPrimary, a, b, strokeWidth = s)
        corner(Offset.Zero, Offset(l, 0f)); corner(Offset.Zero, Offset(0f, l))
        corner(Offset(size.width, 0f), Offset(size.width - l, 0f)); corner(Offset(size.width, 0f), Offset(size.width, l))
        corner(Offset(0f, size.height), Offset(l, size.height)); corner(Offset(0f, size.height), Offset(0f, size.height - l))
        corner(Offset(size.width, size.height), Offset(size.width - l, size.height)); corner(Offset(size.width, size.height), Offset(size.width, size.height - l))
    }
}

@Composable
private fun RoundToolButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(54.dp).clip(CircleShape).background(MiniCard).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = label, tint = MiniTextSecondary)
    }
}
