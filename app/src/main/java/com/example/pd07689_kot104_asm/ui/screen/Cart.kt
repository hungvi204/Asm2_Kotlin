package com.example.pd07689_kot104_asm.ui.screen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pd07689_kot104_asm.R
import com.example.pd07689_kot104_asm.components.ItemCart
import com.example.pd07689_kot104_asm.getUserIdFromPreferences
import com.example.pd07689_kot104_asm.navigation.Screen
import com.example.pd07689_kot104_asm.viewModel.ViewModelCart
import com.example.pd07689_kot104_asm.viewModel.ViewModelCategory

@Preview(showBackground = true)
@Composable
fun Cart(navController: NavController? = null) {
    Scaffold(
        topBar = {
            thanhTopbar(navController)
        },
        content = {
            mainListCart(it,navController)
        }
    )
}


@Composable
private fun mainListCart(paddingValues: PaddingValues,navController: NavController? = null) {


    val viewModelCart: ViewModelCart = viewModel()
    val cartList by viewModelCart.listCart
    Log.d("cart List", cartList.toString())

    val context = LocalContext.current
    val userId = getUserIdFromPreferences(context)

    LaunchedEffect(Unit) {
        if (userId != null) {
            viewModelCart.listCartViewModel(userId)
        } else {
            Log.d("TAG", "User ID not found in SharedPreferences")
        }
    }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .padding(bottom = 20.dp)
        ) {
            items(cartList) { item ->
                if (userId != null) {
                    ItemCart(
                        image = item.image,
                        name = item.name,
                        price = item.price,
                        quantity = item.quantity,
                        idProduct = item.idProduct,
                        idUser = userId
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            enterCode()

            Spacer(modifier = Modifier.height(10.dp))

            tinhTien()

            Spacer(modifier = Modifier.height(10.dp))

            checkOut(navController)
        }

    }
}

@Composable
fun enterCode() {
    var code by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            TextField(
                value = code,
                onValueChange = { code = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(358.dp),
                placeholder = {
                    Text(
                        text = "Enter your promo code",
                        color = Color("#999999".toColorInt()),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                        fontWeight = FontWeight(400)
                    )
                }
            )

            IconButton(
                onClick = { },
                Modifier
                    .background(
                        Color("#303030".toColorInt()),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .width(55.dp)
                    .height(55.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.enter_icon),
                    contentDescription = "search_icon",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
            }


        }

    }
}

@Composable
private fun tinhTien() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,

                ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
            text = "Total:",
            fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
            fontWeight = FontWeight(700),
            fontSize = 20.sp,
            color = Color("#808080".toColorInt())
        )

        Text(
            text = "$ 95.00",
            fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
            fontWeight = FontWeight(700),
            fontSize = 20.sp,
            color = Color("#303030".toColorInt())
        )
    }
}

@Composable
private fun checkOut(navController: NavController? = null) {
    Button(
        onClick = {
            navController?.navigate(Screen.CheckOut.route)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color("#242424".toColorInt())
        ),
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
            )
            .background(
                Color("#242424".toColorInt()),
                RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = "Check out",
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
            fontWeight = FontWeight(600),
            fontSize = 20.sp,
        )
    }
}


@Composable
private fun thanhTopbar(navController: NavController? = null) {

    Box(
        Modifier.padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color("#fefefe".toColorInt()))
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 30.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "My Cart",
                fontFamily = FontFamily(Font(R.font.merriweather_regular)),
                fontWeight = FontWeight(700),
                fontSize = 16.sp,
                color = Color("#303030".toColorInt())
            )
        }

        Box(
            Modifier.padding(16.dp)
        ) {
            IconButton(
                onClick = {
                    navController?.popBackStack()
                },
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
    }

}