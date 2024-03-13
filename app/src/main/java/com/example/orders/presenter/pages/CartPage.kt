package com.example.orders.presenter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.orders.R
import com.example.orders.domain.models.ProductModel
import com.example.orders.presenter.viewModel.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(
    navController: NavController? = null,
) {
    var textAddress by remember { mutableStateOf("") }
    val viewModel = CartViewModel()
    viewModel.getCartProducts()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xff0f172a)
                ),
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {
                    //
                }
            )
        },
        containerColor = Color(0xff0f172a)
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp,
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_nlw),
                    contentDescription = "",
                    Modifier
                        .width(124.dp)
                        .height(24.dp),
                )
                Text(
                    text = "Seu Carrinho",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W700,
                    ),
                )
                Divider(Modifier.padding(vertical = 16.dp))
                if (viewModel.listProductsCart.isEmpty()) {
                    Text(
                        text = "Seu carrinho está vazio",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    )
                } else {
                    for (product in viewModel.listProductsCart)
                        ProductItem(
                            {

                            },
                            ProductModel(
                                name = product.name,
                                description = product.description,
                                image = product.image,
                                price = product.price,
                                ingredients = product.ingredients,
                                type = product.type,
                            ),
                        )
                }


                Divider(Modifier.padding(vertical = 16.dp))

                Row {
                    Text(
                        text = "Total",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                        ),
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = "R$ ${viewModel.calculateTotal()}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color(0xffA3E635),
                            fontWeight = FontWeight.W600,
                        ),
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                TextField(
                    value = textAddress,
                    onValueChange = {
                        textAddress = it
                    },
                    textStyle = TextStyle(
                        color = Color(0xff94a3b8),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(
                            0xff1e293b
                        ),
                        focusedContainerColor = Color(
                            0xff1e293b
                        ),


                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(120.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffA3E635)),
                    modifier = Modifier
                        .fillMaxWidth()


                ) {
                    Row {
                        Text(
                            text = "Enviarr pedido",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            tint = Color.Black,
                            contentDescription = ""
                        )

                    }
                }
                TextButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        navController?.popBackStack()
                    },
                ) {
                    Text(
                        text = "Voltar ao cardápio",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DartPagePreview() {
    CartPage()
}