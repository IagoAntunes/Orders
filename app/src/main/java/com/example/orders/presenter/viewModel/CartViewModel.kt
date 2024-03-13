package com.example.orders.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orders.data.dao.ProductsDao
import com.example.orders.domain.models.ProductModel
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    var dao = ProductsDao()

    var listProductsCart = mutableListOf<ProductModel>()

    fun getCartProducts() {
        viewModelScope.launch {
            var result = dao.getCart()
            listProductsCart = result
        }
    }

    fun calculateTotal():Double {
        var total: Double = 0.0
        listProductsCart.forEach { total += it.price }
        return total
    }
}