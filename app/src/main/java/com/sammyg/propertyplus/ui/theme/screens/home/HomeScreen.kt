package com.sammyg.propertyplus.ui.theme.screens.home

import android.R.attr.button
import android.graphics.ColorSpace
import android.graphics.ColorSpace.Model.RGB
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammyg.propertyplus.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sammyg.propertyplus.navigation.ROUT_CONTACT
import com.sammyg.propertyplus.ui.theme.brightGreen
import java.nio.file.WatchEvent

@Composable
fun HomeScreen(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(158,24,80)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = "FIND YOUR PRODUCT",
            color = Color(255, 255, 255, 255),
            fontSize = 30.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = "The best approach is to be bold!",
            color = Color(234, 234, 234, 255),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
            //fontWeight = FontWeight.Black
        )
        Button(
            onClick = {
                navController.navigate(ROUT_CONTACT)
            },
            colors = ButtonDefaults.buttonColors(brightGreen)
        ) {
            Text(
                text = "Get Started"
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}