package com.example.orders.domain.models

enum class ProductType{
    Food,
    Drink,
}


data class ProductModel(
    val name: String,
    val image: Int,
    val description: String,
    val price: Double,
    val ingredients: MutableList<IngredientModel>,
    val type:ProductType,
)