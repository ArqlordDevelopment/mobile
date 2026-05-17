package com.bakerapps.miniworth.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.components.BodyText
import com.bakerapps.miniworth.components.InfoSectionCard
import com.bakerapps.miniworth.components.ProductCard
import com.bakerapps.miniworth.data.MockProducts
import com.bakerapps.miniworth.ui.theme.MiniPrimary

@Composable
fun CollectionScreen(onProduct: (Int) -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("My Collection", modifier = Modifier.weight(1f), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            IconButton(onClick = { }) { Icon(Icons.Rounded.Add, contentDescription = "Add item", tint = MiniPrimary) }
        }
        InfoSectionCard("12 Items") { BodyText("Estimated value coming soon") }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Owned", "Wishlist", "Sold").forEachIndexed { index, label ->
                FilterChip(selected = index == 0, onClick = { }, label = { Text(label) })
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(MockProducts.products.take(6)) { product ->
                ProductCard(product = product, onClick = { onProduct(product.id) })
            }
        }
    }
}
