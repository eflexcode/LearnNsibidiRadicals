package com.larrex.learnnsibidiradicals.ui.screens

import android.app.Activity
import android.content.Context
import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.Util
import com.larrex.learnnsibidiradicals.model.NsibidiItemModel
import com.larrex.learnnsibidiradicals.ui.conmponent.AdBannerHome
import com.larrex.learnnsibidiradicals.ui.conmponent.NsibidiItem
import com.larrex.learnnsibidiradicals.ui.navigation.NavRouts
import com.larrex.learnnsibidiradicals.ui.theme.ChipBackground
import com.larrex.learnnsibidiradicals.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    var newText by remember { mutableStateOf(TextFieldValue("")) }
    val viewModel = viewModel<MainViewModel>()

    val handler = Handler()
    val colors = listOf<Color>(Color.Transparent, Color.Black)

    val context = LocalContext.current

    var mInterstitialAd2: InterstitialAd? = null


    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        Column {
            AdBannerHome()

            Text(
                text = Util.getGreeting(), modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp),
                textAlign = TextAlign.Start,
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = Util.quicksand
            )

            TextField(
                value = newText,
                onValueChange = { text ->

                    newText = text
                    viewModel.doSearch(text.text)

                },
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp, start = 20.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .height(48.dp),
                colors = TextFieldDefaults.colors(

                    contentColorFor(backgroundColor = ChipBackground),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    ),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(
                        text = "Search",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                },
                textStyle = TextStyle.Default.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = Util.quicksand,
                    fontSize = 12.sp,
                    color = Color.Black
                ),

                )
            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {

                itemsIndexed(
                    if (newText.text.trim()
                            .isEmpty()
                    ) viewModel.nsibidilist else viewModel.nsibidiSearchList
                ) { index, item ->

                    NsibidiItem(navController = navController, nsibidiItemModel = item){


                        navController.currentBackStackEntry?.savedStateHandle?.set("imageclass",item)

                        navController.navigate(NavRouts.DrawingRout.rout)

                    }

                }




            }, contentPadding = PaddingValues(bottom = 60.dp, top = 10.dp))



        }

    }

}