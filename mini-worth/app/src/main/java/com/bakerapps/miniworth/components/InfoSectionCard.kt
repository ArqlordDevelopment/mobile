package com.bakerapps.miniworth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.ui.theme.MiniCard
import com.bakerapps.miniworth.ui.theme.MiniCardBorder
import com.bakerapps.miniworth.ui.theme.MiniPanel
import com.bakerapps.miniworth.ui.theme.MiniTextSecondary

@Composable
fun InfoSectionCard(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MiniCard),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, MiniCardBorder)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Column(Modifier.padding(top = 10.dp), content = content)
        }
    }
}

@Composable
fun BodyText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = MiniTextSecondary,
        style = MaterialTheme.typography.bodyMedium
    )
}
