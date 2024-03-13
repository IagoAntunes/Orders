package com.example.orders.presenter.viewModel

import androidx.lifecycle.ViewModel
import com.example.orders.data.dao.ProductsDao
import com.example.orders.domain.models.ProductModel

class ProductDetailViewModel : ViewModel(){
    val dao = ProductsDao()

    fun addProductCart(productModel: ProductModel) {
        dao.addInCart(productModel)
    }


}