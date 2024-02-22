package com.example.orders.presenter.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.compose.OrdersTheme
import com.example.orders.R
import com.example.orders.data.dao.ProductsDao
import com.example.orders.domain.models.MenuItemModel
import com.example.orders.domain.models.ProductModel
import com.example.orders.domain.models.SectionModel


class HomePageUiState(
    val listMenusFilter: SnapshotStateList<MenuItemModel>,
    val indexOfSelectedMenu: MutableIntState,
    val navController: NavController? = null
) {

    fun navigate(name: String, section: String) {
        Log.i("NOME -->", name)
        navController?.navigate("productDetail/$name/$section")
    }
}

@Composable
fun HomePage2(
    navController: NavController? = null
) {
    val indexOfSelectedMenu = remember {
        mutableIntStateOf(0)
    }
    val listMenusFilter =
        remember {
            mutableStateListOf<MenuItemModel>(
                MenuItemModel(name = "Todos", isSelected = true),
                MenuItemModel(name = "Lanche do dia"),
                MenuItemModel(name = "Promoções"),
                MenuItemModel(name = "Bebidas"),
            )
        }
    val state = remember {
        HomePageUiState(
            listMenusFilter,
            indexOfSelectedMenu,
            navController,
        )
    }

    Log.i("1.oi", (navController == null).toString())
    HomePage(state)
}

@Composable
fun HomePage(state: HomePageUiState) {
    Log.i("11.oi", (state.navController == null).toString())
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xff0f172a)
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                HeadHomePage()
                Divider(Modifier.padding(vertical = 16.dp))
                MenusFilter(
                    state.listMenusFilter,
                    state.indexOfSelectedMenu,
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ListProducts(state)
            }

        }
    }
}


@Composable
fun ListProducts(state: HomePageUiState) {
    val dao = ProductsDao()
    Log.i("2.oi", (state.navController == null).toString())
    Column(Modifier.verticalScroll(state = rememberScrollState())) {
        SectionProducts(
            section = dao.getDayFoodSection(),
            state,
        )
        SectionProducts(
            section = dao.getPromotionsFoodSection(),
            state = state
        )
        SectionProducts(
            section = dao.getDrinks(),
            state = state
        )

    }
}

@Preview()
@Composable
private fun SectionProducts(
    section: SectionModel = SectionModel(
        name = "Lanche do dia",
        listProducts = mutableListOf(
            ProductModel(
                name = "X-React",
                description = "Ola mundo",
                image = R.drawable.hamburguer_1,
                price = 35.90,
                ingredients = mutableListOf()
            )
        )
    ),
    state: HomePageUiState = HomePageUiState(
        listMenusFilter = mutableStateListOf(),
        indexOfSelectedMenu = mutableIntStateOf(0),
    )
) {
    Log.i("3.oi", (state.navController == null).toString())
    Column {
        Text(
            section.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,

                ),
        )
        for (product in section.listProducts) {
            Row(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 8.dp)
                    .clickable {
                        Log.i("oi", (state.navController == null).toString())
                        state.navigate(product.name, section.name)


                    },
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Image(
                    modifier = Modifier.size(92.dp),
                    painter = painterResource(
                        id = product.image,
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                Column() {
                    Text(
                        text = product.name,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    )
                    Text(
                        text = product.description,
                        style = TextStyle(
                            color = Color(0xff94a3b8),
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun MenusFilter(
    listMenusFilter: MutableList<MenuItemModel>,
    indexOfSelectedMenu: MutableIntState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.horizontalScroll(state = rememberScrollState())
    ) {

        for (index in listMenusFilter.indices) {
            MenuItemFilter(
                isSelected = listMenusFilter[index].isSelected,
                text = listMenusFilter[index].name,
                onClick = {
                    indexOfSelectedMenu.intValue = index
                    if (index == indexOfSelectedMenu.value) {
                        listMenusFilter[index].isSelected = true
                    } else {
                        listMenusFilter[index].isSelected = false
                    }
                    Log.i(listMenusFilter[index].name, listMenusFilter[index].isSelected.toString())
                }
            )
        }
    }
}

@Composable
private fun HeadHomePage() {
    Image(
        painter = painterResource(id = R.drawable.logo_nlw),
        contentDescription = "",
        Modifier
            .width(124.dp)
            .height(24.dp),

        )
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Faça seu pedido",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.W700,
            ),
        )
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "",
            tint = Color.White,
        )
    }
}

@Composable
private fun MenuItemFilter(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    text: String = "Ola",
    onClick: () -> Unit = {},
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(6.dp))
            .run {
                if (isSelected) {
                    border(
                        1.dp,
                        Color(0xffA3E635),
                        shape = RoundedCornerShape(6.dp)
                    ) // Add a 1dp white border if useDarkTheme is true
                } else {
                    this // Return the original modifier if useDarkTheme is false
                }
            }
            .background(Color(0xff1e293b))
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .clickable {
                onClick()
            }

    ) {
        Text(
            text = text,
            style = TextStyle(color = Color.White),
        )
    }
}

@Preview
@Composable
private fun MenuItemFilterSelected() {
    MenuItemFilter(isSelected = true)
}

@Preview
@Composable
private fun HomePagePreview() {
    OrdersTheme(useDarkTheme = true) {
        HomePage2()
    }
}