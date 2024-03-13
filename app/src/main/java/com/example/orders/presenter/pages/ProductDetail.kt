package com.example.orders.presenter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.orders.R
import com.example.orders.presenter.viewModel.ProductDetailViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun ProductDetail(
    navController: NavController? = null,
    name: String,
    section: String,
) {
    val viewModel = ProductDetailViewModel()
    val product = viewModel.dao.getProductByName(name, section)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Color(0xff0f172a),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(Color(0xff0f172a)),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            navController?.popBackStack()
                        }
                    )
                },
                title = {
                    Text(
                        text = "Detalhes do produto",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                        ),
                    )
                },
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                Modifier
                    .padding()
                    .fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
                Column(
                    Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp,
                    )
                ) {
                    Text(
                        text = product.name,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "R$ ${product.price}",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W600,
                            color = Color(0xffA3E635)
                        ),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Text(
                        text = LoremIpsum(16).values.first(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            color = Color(0XFF94a3b8),
                            textAlign = TextAlign.Justify
                        )
                    )
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                    for (item in product.ingredients) {
                        Text(
                            text = "- ${item.name}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W600,
                                color = Color(0XFF94a3b8),
                                textAlign = TextAlign.Justify
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            viewModel.addProductCart(product)
                            scope.launch {
                                snackbarHostState.showSnackbar("Adicionado ao Carrinho!")
                            }
                        },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffA3E635)),
                        modifier = Modifier
                            .fillMaxWidth()


                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Add,
                                tint = Color.Black,
                                contentDescription = ""
                            )
                            Text(
                                text = "Adicionar ao pedido",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W400,
                                )
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
}
