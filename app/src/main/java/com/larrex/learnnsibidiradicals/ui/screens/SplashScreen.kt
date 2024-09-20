package com.larrex.learnnsibidiradicals.ui.screens

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.Util
import com.larrex.learnnsibidiradicals.ui.navigation.NavRouts

@Composable
fun SplashScreen(navController: NavController) {





    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(Color.Transparent, true)

        val colors = listOf<Color>(Color.Transparent, Color.Black)

        val handler = Handler()
        LaunchedEffect(Unit) {
            handler.postDelayed({
                navController.navigate(NavRouts.HomeRout.rout) {
                    popUpTo(0) {
                    }
                }

            }, 3000)
        }


        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.sharpen_igbo_girl_dancing_2_slime_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val colorList = Brush.verticalGradient(colors)
                    onDrawWithContent {
                        drawContent()
                        drawRect(colorList, blendMode = BlendMode.Multiply)
                    }
                },
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "黄字",
                textAlign = TextAlign.Center,
                fontSize = 60.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontFamily = Util.nsibidi,
                style = TextStyle.Default

            )

            Text(
                text = "Learn Nsịbìdī Characters",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontFamily = Util.quicksand,
                style = TextStyle.Default

            )

        }

    }

}