package com.bakerapps.miniworth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bakerapps.miniworth.ui.theme.MiniPrimary

@Composable
fun ConfidenceBadge(confidence: Int, modifier: Modifier = Modifier) {
    Text(
        text = "$confidence% Match",
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MiniPrimary.copy(alpha = 0.18f))
            .height(28.dp)
            .padding(PaddingValues(horizontal = 10.dp, vertical = 5.dp)),
        color = MiniPrimary,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold
    )
}
