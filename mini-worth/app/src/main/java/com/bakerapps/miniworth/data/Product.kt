package com.bakerapps.miniworth.data

data class Product(
    val id: Int,
    val name: String,
    val gameSystem: String,
    val faction: String,
    val productType: String,
    val category: String,
    val description: String,
    val contents: List<String>,
    val keywords: List<String>,
    val confidence: Int,
    val ebaySearchQuery: String
)
