package com.example.pd07689_kot104_asm.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pd07689_kot104_asm.R
import com.example.pd07689_kot104_asm.viewModel.ViewModelCart
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun ItemCart(image: String, name: String, price: Float, quantity: Int, idProduct: String, idUser :String) {

    val viewModelCart: ViewModelCart = viewModel()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Xác nhận xoá") },
            text = { Text("Bạn có chắc chắn muốn xoá sản phẩm này không?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Thực hiện thao tác xoá sản phẩm
                        viewModelCart.deleteCartById(idUser, idProduct)
                        Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    }
                ) {
                    Text("Xoá")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Hủy")
                }
            }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        // ảnh sản phẩm
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = image,
                contentScale = ContentScale.FillBounds,
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color("#F0F0F0".toColorInt()),
                        shape = RoundedCornerShape(10.dp)
                    ),
            )

        }

        // thông tin sản phẩm
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                color = Color("#999999".toColorInt())
            )

            Text(
                text = "$ ${price.toInt()}",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                color = Color("#242424".toColorInt())
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(113.dp)
            ) {

                IconButton(
                    onClick = {
                        if (idUser != null) {
                            viewModelCart.addToCart(idUser ,idProduct)
                        }
                    },
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
                        fontSize = 20.sp,
                    )
                }

                Text(
                    text = "$quantity",
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
                        if (idUser != null) {
                            viewModelCart.descreaseCart(idUser ,idProduct)
                        }
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
                        fontSize = 20.sp,
                    )
                }
            }
        }

        // xóa sản phẩm
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            IconButton(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
            }
        }
    }
}
