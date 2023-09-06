package com.larrex.learnnsibidiradicals.ui.screens

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.Util
import com.larrex.learnnsibidiradicals.model.ColorItem
import com.larrex.learnnsibidiradicals.model.DrawLine
import com.larrex.learnnsibidiradicals.model.NsibidiItemModel
import com.larrex.learnnsibidiradicals.ui.conmponent.AdBannerDraw
import com.larrex.learnnsibidiradicals.ui.conmponent.ColorComponent
import com.larrex.learnnsibidiradicals.ui.theme.Black
import com.larrex.learnnsibidiradicals.ui.theme.Black2
import com.larrex.learnnsibidiradicals.ui.theme.Blue
import com.larrex.learnnsibidiradicals.ui.theme.Green
import com.larrex.learnnsibidiradicals.ui.theme.IgboBlue
import com.larrex.learnnsibidiradicals.ui.theme.Orange
import com.larrex.learnnsibidiradicals.ui.theme.Pink
import com.larrex.learnnsibidiradicals.ui.theme.Purple
import com.larrex.learnnsibidiradicals.ui.theme.Yellow
import com.larrex.learnnsibidiradicals.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawingScreen(navController: NavController, nsibidiItemModel: NsibidiItemModel) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.Transparent, true)

    var showSheet by remember {
        mutableStateOf(false)
    }
    val controller = rememberColorPickerController()

    val supportedColors = listOf<ColorItem>(
        ColorItem(Black, true),
        ColorItem(Blue, false),
        ColorItem(Orange, false),
        ColorItem(Yellow, false),
        ColorItem(Green, false),
        ColorItem(Pink, false),
        ColorItem(Purple, false),
        ColorItem(Black2, false)
    )

    var sliderPosition by remember {
        mutableStateOf(10f)
    }

    val viewmodel = viewModel<MainViewModel>()

    var currentColors by remember { mutableStateOf(Color.Black) }
    var currentWidth by remember { mutableStateOf(5.dp) }

//    var currentColors = Color.Black

    val context = LocalContext.current

    Toast.makeText(context,"Click setting icon to change color if you clicked eraser click setting can click color again",Toast.LENGTH_LONG).show()

    val isItemSaved = context.getSharedPreferences("adCount", Context.MODE_PRIVATE)
    val adCount = isItemSaved.getInt("ad", 0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {



        Column(modifier = Modifier.fillMaxWidth()) {


            Box(modifier = Modifier.weight(1f)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {

                    AdBannerDraw()

                    Image(
                        painter = rememberAsyncImagePainter(model = nsibidiItemModel.imageId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )

                    Text(
                        text = nsibidiItemModel.word,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = Util.quicksand,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle.Default,
                        modifier = Modifier.padding(bottom = 10.dp)

                    )

                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(20.dp)
            ) {

                Column() {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(7.dp), Arrangement.End
                    ) {

//                        Icon(
//                            painter = rememberAsyncImagePainter(model = R.drawable.round_save),
//                            contentDescription = "",
//                            modifier = Modifier.padding(5.dp)
//                        )

//                        Box(
//                            modifier = Modifier
//                                .weight(1f)
//                                .width(150.dp)
//                        ) {
//
//                            Button(
//                                onClick = { },
//                                border = BorderStroke(1.dp, Color.Black),
//                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//                                modifier = Modifier
//                            ) {
//
//                                Text(text = "Clear", color = Color.Black)
//
//                            }
//                        }
                        
                        Row(
                            modifier = Modifier.weight(0.3f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(model = R.drawable.eraser),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(30.dp)
                                    .clickable {
                                        currentColors = Color.White
                                    }
                            )

                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.round_settings),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(30.dp)
                                    .clickable { showSheet = true },
                                tint = Color.Black

                            )
                        }


                    }

                    Canvas(modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(true) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()

                                val line = DrawLine(
                                    start = change.position - dragAmount,
                                    end = change.position,
                                    color = currentColors,
                                    strokeWidth = currentWidth
                                )

                                viewmodel.drawLineList.add(line)

                            }
                        }, onDraw = {

                        viewmodel.drawLineList.forEach { line ->

                            drawLine(
                                color = line.color,
                                start = line.start,
                                end = line.end,
                                cap = StrokeCap.Round,
                                strokeWidth = line.strokeWidth.toPx()
                            )

                        }

                    })

                }

            }

        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showSheet = false
                },
                sheetState = rememberModalBottomSheetState(false),
                containerColor = Color.White
            ) {

                Column(
                    modifier = Modifier.background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Column(
                        Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Colors \uD83C\uDFA8",
                            modifier = Modifier.padding(start = 20.dp, bottom = 7.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Util.quicksand,
                            color = Color.Black
                        )

                        LazyRow(
                            content = {

                                itemsIndexed(supportedColors) { index, it ->

                                    ColorComponent(
                                        color = it.color,
                                        viewmodel.selectedColor.value == index
                                    ) {

                                        viewmodel.selectedColor.value = index

                                        currentColors = it.color

                                    }

                                    Spacer(modifier = Modifier.width(15.dp))

                                }
                            },
                            contentPadding = PaddingValues(start = 30.dp, end = 30.dp),
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        Text(
                            text = "Brush thickness \uD83D\uDD8C",
                            modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Util.quicksand,
                            color = Color.Black

                        )

                        Column(
                            modifier = Modifier.padding(
                                bottom = 7.dp, start = 40.dp, end = 40.dp
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Canvas(modifier = Modifier
                                .height(26.dp)
                                .width(50.dp), onDraw = {

                                drawLine(
                                    color = Black,
                                    strokeWidth = sliderPosition,
                                    cap = StrokeCap.Round,
                                    start = Offset(x = 0f, y = size.height),
                                    end = Offset(
                                        x = size.width, y = size.height
                                    )
                                )

                            })

                            Slider(
                                value = sliderPosition,
                                onValueChange = {
                                    sliderPosition = it
                                    currentWidth = it.toInt().dp
                                },
                                valueRange = 5f..25f,
                                steps = 5,
                                colors = SliderDefaults.colors(
                                    thumbColor = IgboBlue, activeTrackColor = IgboBlue
                                )
                            )

                        }

                    }

//                    Button(
//                        onClick = { showSheet = false },
//                        modifier = Modifier
//                            .padding(bottom = 7.dp, top = 7.dp)
//                            .width(250.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = IgboBlue)
//                    ) {
//                        Text(
//                            text = "Save", fontFamily = Util.quicksand,
//                        )
//                    }

                }

            }
        }

    }

}