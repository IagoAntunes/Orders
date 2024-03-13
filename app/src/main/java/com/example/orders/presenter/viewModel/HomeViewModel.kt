package com.example.orders.presenter.viewModel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.orders.data.dao.ProductsDao
import com.example.orders.domain.models.MenuItemModel
import com.example.orders.domain.models.ProductModel

class HomeViewModel() : ViewModel() {

    val dao = ProductsDao()
    var navController: NavController? = null


    var listMenusFilter =
        mutableStateListOf<MenuItemModel>(
            MenuItemModel(name = "Todos", isSelected = true),
            MenuItemModel(name = "Lanche do dia"),
            MenuItemModel(name = "Promoções"),
            MenuItemModel(name = "Bebidas"),
        )
    var indexOfSelectedMenu =
        mutableStateOf("Todos")

    fun navigate(name: String, section: String) {
        navController?.navigate("productDetail/$name/$section")
    }


    fun getFoods(): List<ProductModel> {
        return dao.getFoods()
    }

    fun changeMenuSelected(
        item: MenuItemModel,
        index: Int
    ) {
        indexOfSelectedMenu.value = item.name
        for (menu in listMenusFilter) {
            if (menu.name != item.name) {
                menu.isSelected = false
            }
        }
        if (item.name == indexOfSelectedMenu.value) {
            listMenusFilter[index] = item.copy(isSelected = true)
        }
    }
}