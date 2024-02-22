package com.example.orders.domain.models

data class ProductModel(
    val name: String,
    val image: Int,
    val description: String,
    val price: Double,
    val ingredients: MutableList<IngredientModel>
)