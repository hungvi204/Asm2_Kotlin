package com.example.pd07689_kot104_asm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pd07689_kot104_asm.R
import com.example.pd07689_kot104_asm.navigation.Screen
import com.example.pd07689_kot104_asm.response.ProductResponse

@Composable
fun ItemGrid(model: ProductResponse, navController: NavController? = null) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                bottom = 16.dp
            )
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    onClick = {
                        navController?.navigate("${Screen.ProductDetail.route}/${model.productID}")
                    }
                )
        ) {
            AsyncImage(
                model = model.image,
                contentScale = ContentScale.FillBounds,
                contentDescription = "",
                modifier = Modifier
                    .height(200.dp)
                    .width(157.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )


            Image(
                painter = painterResource(id = R.drawable.addtocart),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(
                    start = 15.dp
                )
                .align(Alignment.Start)
        ) {
            Text(
                text = model.productName,
                color = Color("#606060".toColorInt()),
                fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                fontWeight = FontWeight(400),
                fontSize = 14.sp,

                )

            Text(
                text = "$${model.price}",
                color = Color("#303030".toColorInt()),
                fontFamily = FontFamily(Font(R.font.nunitosans_regular)),
                fontWeight = FontWeight(700),
                fontSize = 14.sp
            )
        }
    }
}