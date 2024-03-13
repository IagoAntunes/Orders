package com.example.orders.data.dao

import android.util.Log
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.orders.R
import com.example.orders.domain.models.IngredientModel
import com.example.orders.domain.models.ProductModel
import com.example.orders.domain.models.ProductType
import com.example.orders.domain.models.SectionModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductsDao {

    companion object {
        val carts = mutableListOf<ProductModel>(
        )
        val allFoods = MutableStateFlow<List<ProductModel>>(
            listOf(
                ProductModel(
                    name = "X-JavaScript",
                    description = LoremIpsum(16).values.first(),
                    image = R.drawable.hamburguer_2,
                    price = 24.90,
                    type = ProductType.Food,
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
                    type = ProductType.Food,
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
                    type = ProductType.Food,
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
    }

    fun addInCart(product: ProductModel) {
        carts.add(product)
        Log.i("TAMANHO", carts.size.toString())
    }

    fun getCart() = carts
    fun getFoods() = allFoods.asStateFlow().value.filter { it.type == ProductType.Food }

    fun getProductByName(name: String, nameSection: String): ProductModel {
        val product = when (nameSection) {
            "Promoções" -> {
                allFoods.value.find { it.name == name }
            }


            else -> {
                allFoods.value.find { it.name == name }
            }
        }
        return product!!
    }
}