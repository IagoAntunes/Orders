package com.example.orders.data.dao

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.orders.R
import com.example.orders.domain.models.IngredientModel
import com.example.orders.domain.models.ProductModel
import com.example.orders.domain.models.SectionModel

class ProductsDao {

    companion object {
        val promotionsFood = SectionModel(
            name = "Promoções", listProducts = mutableListOf(
                ProductModel(
                    name = "X-JavaScript",
                    description = LoremIpsum(16).values.first(),
                    image = R.drawable.hamburguer_2,
                    price = 24.90,
                    ingredients = mutableListOf(
                        IngredientModel(name = "Pão brioche;"),
                        IngredientModel(name = "2x carnes smash (blend da casa) de 80g;"),
                        IngredientModel(name = "Queijo cheddar;"),
                        IngredientModel(name = "Alface;"),
                        IngredientModel(name = "Tomate;"),
                        IngredientModel(name = "Picles;"),
                        IngredientModel(name = "Molho de casa;"),
                    )
                ),
                ProductModel(
                    name = "X-Cobol",
                    description = LoremIpsum(16).values.first(),
                    image = R.drawable.hamburguer_3,
                    price = 22.90,
                    ingredients = mutableListOf(
                        IngredientModel(name = "Pão brioche;"),
                        IngredientModel(name = "2x carnes smash (blend da casa) de 80g;"),
                        IngredientModel(name = "Queijo cheddar;"),
                        IngredientModel(name = "Alface;"),
                        IngredientModel(name = "Tomate;"),
                        IngredientModel(name = "Picles;"),
                        IngredientModel(name = "Molho de casa;"),
                    )
                ),
                ProductModel(
                    name = "X-Tailwind",
                    description = LoremIpsum(16).values.first(),
                    image = R.drawable.hamburguer_4,
                    price = 13.90,
                    ingredients = mutableListOf(
                        IngredientModel(name = "Pão brioche;"),
                        IngredientModel(name = "2x carnes smash (blend da casa) de 80g;"),
                        IngredientModel(name = "Queijo cheddar;"),
                        IngredientModel(name = "Alface;"),
                        IngredientModel(name = "Tomate;"),
                        IngredientModel(name = "Picles;"),
                        IngredientModel(name = "Molho de casa;"),
                    )
                )
            )
        )
        val dayFood = SectionModel(
            name = "Lanche do dia", listProducts = mutableListOf(
                ProductModel(
                    name = "X-React",
                    description = LoremIpsum(16).values.first(),
                    image = R.drawable.hamburguer_1,
                    price = 56.90,
                    ingredients = mutableListOf(
                        IngredientModel(name = "Pão brioche;"),
                        IngredientModel(name = "2x carnes smash (blend da casa) de 80g;"),
                        IngredientModel(name = "Queijo cheddar;"),
                        IngredientModel(name = "Alface;"),
                        IngredientModel(name = "Tomate;"),
                        IngredientModel(name = "Picles;"),
                        IngredientModel(name = "Molho de casa;"),
                    )
                )
            )
        )
        val drinks = SectionModel(
            name = "Bebidas", listProducts = mutableListOf(
                ProductModel(
                    name = "Hmmm, coquinha!",
                    description = LoremIpsum(16).values.first(),
                    image = R.drawable.drink_2,
                    price = 8.90,
                    ingredients = mutableListOf()
                )
            )
        )
    }

    fun getDayFoodSection() = dayFood
    fun getPromotionsFoodSection() = promotionsFood
    fun getDrinks() = drinks

    fun getProductByName(name: String, nameSection: String): ProductModel {
        val product = when (nameSection) {
            "Promoções" -> {
                promotionsFood.listProducts.find { it.name == name }
            }

            "Lanche do dia" -> {
                dayFood.listProducts.find { it.name == name }
            }

            "Bebidas" -> {
                drinks.listProducts.find { it.name == name }
            }

            else -> {
                dayFood.listProducts.find { it.name == name }
            }
        }
        return product!!
    }
}