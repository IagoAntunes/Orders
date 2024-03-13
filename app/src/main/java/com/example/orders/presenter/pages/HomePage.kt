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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.compose.OrdersTheme
import com.example.orders.R
import com.example.orders.data.dao.ProductsDao
import com.example.orders.domain.models.MenuItemModel
import com.example.orders.domain.models.ProductModel
import com.example.orders.presenter.viewModel.HomeViewModel


@Composable
fun HomePage2(
    navController: NavController? = null,
    viewModel: HomeViewModel,
    navigateProduct: () -> Unit,
) {
    viewModel.navController = navController
    HomePage(viewModel)
}

@Composable
fun HomePage(viewModel: HomeViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), containerColor = Color(0xff0f172a)
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                HeadHomePage(viewModel)
                Divider(Modifier.padding(vertical = 16.dp))
                MenusFilter(
                    viewModel,
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                ListProducts(viewModel)
            }

        }
    }
}


@Composable
fun ListProducts(viewModel: HomeViewModel) {
    Column(Modifier.verticalScroll(state = rememberScrollState())) {
        SectionProducts(
            products = viewModel.getFoods(),
            viewModel,
        )
    }
}

@Composable
private fun SectionProducts(
    products: List<ProductModel>,
    viewModel: HomeViewModel,
) {
    Column {
        Text(
            "SEÇÃO",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,

                ),
        )
        for (product in products) {
            ProductItem(
                {
                    viewModel.navigate(product.name, "awda")
                },
                product,
            )
        }
    }
}

@Composable
fun ProductItem(
    onClick: () -> Unit,
    product: ProductModel,
) {
    Row(
        modifier = Modifier
            .padding(top = 6.dp, bottom = 8.dp)
            .clickable {
                onClick()
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
                text = product.name, style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                )
            )
            Text(
                text = product.description, style = TextStyle(
                    color = Color(0xff94a3b8), fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
private fun MenusFilter(
    viewModel: HomeViewModel,

    ) {
    val itemStates = remember {
        mutableStateOf(viewModel.listMenusFilter)
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.horizontalScroll(state = rememberScrollState())
    ) {

        for ((index, item) in itemStates.value.withIndex()) {

            MenuItemFilter(
                isSelected = item.isSelected,
                text = item.name,
                onClick = {
                    viewModel.changeMenuSelected(item, index)
                },
            )
        }
    }
}

@Composable
private fun HeadHomePage(viewModel: HomeViewModel) {
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
            modifier = Modifier.clickable {
                viewModel.navController?.navigate("cart")
            }
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
                        1.dp, Color(0xffA3E635), shape = RoundedCornerShape(6.dp)
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

