package com.example.pd07689_kot104_asm.ui.screen

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pd07689_kot104_asm.R
import com.example.pd07689_kot104_asm.getUserIdFromPreferences
import com.example.pd07689_kot104_asm.navigation.Screen
import com.example.pd07689_kot104_asm.viewModel.ViewModelCart
import com.example.pd07689_kot104_asm.viewModel.ViewModelProduct


@Composable
fun ProductDetail(
    productId: String,
    navController: NavController? = null,
    viewModelProduct: ViewModelProduct = viewModel()
) {

    val detailProduct by viewModelProduct.detailProduct
    val viewModelCart: ViewModelCart = viewModel()
    val context = LocalContext.current;
    val idUser = getUserIdFromPreferences(context);

    LaunchedEffect(Unit) {
        viewModelProduct.detailProductViewModel(productId)
    }

    var number by remember { mutableStateOf(1) }

    fun addCart() {
        if (idUser != null) {
            detailProduct?.let { viewModelCart.addToCart(idUser, it.productID,number) }
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show()

        };
    }

    Box {
        Box(
            modifier = Modifier
                .padding(
                    start = 50.dp,
                    top = 50.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
                .zIndex(1f)
        ) {
            IconButton(
                onClick = {
                    navController?.popBackStack()
                },
                Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .width(40.dp)
                    .height(40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "search_icon",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    top = 150.dp
                )
                .zIndex(1f)
        ) {
            Column(
                modifier = Modifier
                    .width(64.dp)
                    .height(192.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(50.dp)
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .size(34.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .border(
                            width = 5.dp,
                            color = Color("#909090".toColorInt()),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {}

                Column(
                    modifier = Modifier
                        .size(34.dp)
                        .background(
                            Color("#B4916C".toColorInt()),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .border(
                            width = 5.dp,
                            color = Color("#F0F0F0".toColorInt()),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {}

                Column(
                    modifier = Modifier
                        .size(34.dp)
                        .background(
                            Color("#E4CBAD".toColorInt()),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .border(
                            width = 5.dp,
                            color = Color("#F0F0F0".toColorInt()),
                            shape = RoundedCornerShape(50.dp)
                        )
                ) {}
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = detailProduct?.image,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(327.dp)
                    .height(455.dp)
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(bottomStart = 50.dp))
            )

            Column(
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 20.dp,
                        end = 20.dp,
                    )
                    .fillMaxWidth()
                    .clickable(
                        onClick = {})
            ) {

                // tên sản phẩm
                detailProduct?.let {
                    Text(
                        text = it.productName,
                        fontFamily = FontFamily(Font(R.font.gelasio_regular)),
                        fontWeight = FontWeight(500),
                        fontSize = 24.sp,
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    // giá
                    Text(
                        text = "$ ${detailProduct?.price}",
                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                        fontWeight = FontWeight(700),
                        fontSize = 30.sp,
                    )

                    // số lượng
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(113.dp)
                    ) {

                        IconButton(
                            onClick = { number++ },
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    Color("#F0F0F0".toColorInt()),
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Text(
                                text = "+",
                                color = Color("#242424".toColorInt()),
                                fontSize = 24.sp,
                            )
                        }

                        Text(
                            text = number.toString(),
                            fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                            fontWeight = FontWeight(600),
                            fontSize = 18.sp,
                            color = Color("#242424".toColorInt()),
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp
                            )
                        )

                        IconButton(
                            onClick = {
                                if(number > 1)  number--
                            },
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    Color("#F0F0F0".toColorInt()),
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Text(
                                text = "-",
                                color = Color("#242424".toColorInt()),
                                fontSize = 24.sp,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // đánh giá
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gold_star_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "4.8",
                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp,
                        color = Color("#303030".toColorInt())
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = "(50 reviews)",
                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        color = Color("#808080".toColorInt())
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                // mô tả
                detailProduct?.description?.let {
                    Text(
                        text = it,
                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                        fontWeight = FontWeight(300),
                        fontSize = 14.sp,
                        color = Color("#606060".toColorInt()),
                    )
                }

            }
        }

        Box(
            modifier = Modifier
                .padding(
                    bottom = 20.dp,
                    start = 20.dp,
                    top = 780.dp,
                    end = 20.dp
                )
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            Color("#F0F0F0".toColorInt()),
                            RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.addtofavourite),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }

                Button(
                    onClick = {
                        addCart();
                        navController?.navigate(Screen.Cart.route)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#242424".toColorInt())
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(250.dp)
                        .background(
                            Color("#242424".toColorInt()),
                            RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        text = "Add to cart",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                        fontWeight = FontWeight(600),
                        fontSize = 20.sp,
                    )
                }
            }
        }


    }
}