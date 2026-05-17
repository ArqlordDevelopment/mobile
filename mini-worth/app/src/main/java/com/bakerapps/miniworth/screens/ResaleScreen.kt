package com.bakerapps.miniworth.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.components.BodyText
import com.bakerapps.miniworth.components.InfoSectionCard
import com.bakerapps.miniworth.data.Product
import com.bakerapps.miniworth.ui.theme.MiniBackground
import com.bakerapps.miniworth.ui.theme.MiniPrimary

@Composable
fun ResaleScreen(product: Product, onBack: () -> Unit) {
    val context = LocalContext.current
    var condition by remember { mutableStateOf("Sealed") }
    Scaffold(
        containerColor = MiniBackground,
        topBar = {
            Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back") }
                Text("Resale Guide", modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            InfoSectionCard(product.name) { BodyText(product.faction) }
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Sealed", "New on Sprue", "Built", "Painted", "Incomplete / Bits").forEach {
                    FilterChip(selected = condition == it, onClick = { condition = it }, label = { Text(it) })
                }
            }
            InfoSectionCard("Market guidance") {
                BodyText("Use these links to compare current eBay listings. Sold-price API support will be added later.")
                BodyText("Selected condition: $condition", Modifier.padding(top = 8.dp))
            }
            InfoSectionCard("Estimated guidance") {
                listOf(
                    "Sealed: Highest demand",
                    "New on Sprue: Good for hobbyists",
                    "Built/Painted: Compare by paint quality and completeness",
                    "Bits: Search specific weapons, heads, and sprues"
                ).forEach { BodyText(it, Modifier.padding(bottom = 6.dp)) }
            }
            listOf(
                "Search All Listings" to "",
                "Built / Painted" to " built painted",
                "New on Sprue" to " new on sprue",
                "Bits" to " bits"
            ).forEach { (label, extra) ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MiniPrimary, contentColor = MiniBackground),
                    onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(product.ebayUrl(extra)))) }
                ) { Text(label, fontWeight = FontWeight.Bold) }
            }
            // TODO: Add eBay Browse API integration for live listings and sold-price signals.
            // TODO: Add Google Cloud API integration, user accounts, and cloud collection sync.
        }
    }
}
