package com.bakerapps.miniworth.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.components.BodyText
import com.bakerapps.miniworth.components.ConfidenceBadge
import com.bakerapps.miniworth.components.InfoSectionCard
import com.bakerapps.miniworth.components.ProductImagePlaceholder
import com.bakerapps.miniworth.data.Product
import com.bakerapps.miniworth.ui.theme.MiniBackground
import com.bakerapps.miniworth.ui.theme.MiniPrimary
import com.bakerapps.miniworth.ui.theme.MiniTextSecondary
import kotlinx.coroutines.launch

@Composable
fun ScanResultScreen(
    product: Product,
    onBack: () -> Unit,
    onResale: (Int) -> Unit
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        containerColor = MiniBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back") }
                Text("Scan Result", modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                IconButton(onClick = { }) { Icon(Icons.Rounded.MoreVert, contentDescription = "More") }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            InfoSectionCard(title = "") {
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    ProductImagePlaceholder(Modifier.size(width = 118.dp, height = 98.dp))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        ConfidenceBadge(product.confidence)
                        Text(product.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(product.faction, color = MiniPrimary, style = MaterialTheme.typography.bodyMedium)
                        Text(product.gameSystem, color = MiniTextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text(product.productType, color = MiniTextSecondary, style = MaterialTheme.typography.bodySmall)
                    }
                }
                FlowRow(Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(product.category, "Troops", product.faction).forEach { AssistChip(onClick = { }, label = { Text(it) }) }
                }
            }

            InfoSectionCard("About this set") { BodyText(product.description) }
            InfoSectionCard("Common contents") {
                product.contents.forEach { BodyText("- $it", Modifier.padding(bottom = 4.dp)) }
            }
            InfoSectionCard("Condition guidance") {
                listOf(
                    "Sealed: Highest normal resale value",
                    "New on Sprue: Strong resale value",
                    "Built / Unpainted: Medium value",
                    "Painted: Depends heavily on paint quality",
                    "Incomplete / Bits: Lower value, but useful for parts"
                ).forEach { BodyText(it, Modifier.padding(bottom = 6.dp)) }
            }
            InfoSectionCard("Best resale keywords") {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    product.keywords.forEach { AssistChip(onClick = { }, label = { Text(it) }) }
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MiniPrimary, contentColor = MiniBackground),
                onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(product.ebayUrl()))) }
            ) { Text("View on eBay", fontWeight = FontWeight.Bold) }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { scope.launch { snackbarHostState.showSnackbar("Saved to collection") } }
            ) { Text("Save to Collection") }
            OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { onResale(product.id) }) { Text("Open Resale Guide") }
        }
    }
}

fun Product.ebayUrl(extra: String = ""): String {
    val query = (ebaySearchQuery + extra).trim().replace(" ", "+")
    return "https://www.ebay.com/sch/i.html?_nkw=$query"
}
