package com.larrex.learnnsibidiradicals.ui.screens

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.RectF
import android.os.Handler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.Util
import com.larrex.learnnsibidiradicals.model.ColorItem
import com.larrex.learnnsibidiradicals.ui.conmponent.ColorComponent
import com.larrex.learnnsibidiradicals.ui.theme.Black
import com.larrex.learnnsibidiradicals.ui.theme.Black2
import com.larrex.learnnsibidiradicals.ui.theme.Blue
import com.larrex.learnnsibidiradicals.ui.theme.Green
import com.larrex.learnnsibidiradicals.ui.theme.IgboBlue
import com.larrex.learnnsibidiradicals.ui.theme.Orange
import com.larrex.learnnsibidiradicals.ui.theme.Pink
import com.larrex.learnnsibidiradicals.ui.theme.Purple
import com.larrex.learnnsibidiradicals.ui.theme.White
import com.larrex.learnnsibidiradicals.ui.theme.Yellow
import com.larrex.learnnsibidiradicals.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Button(onClick = { showSheet = true }) {
            Text(text = "Show")
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

                            )

                        LazyRow(
                            content = {

                                itemsIndexed(supportedColors) { index, it ->

                                    ColorComponent(
                                        color = it.color,
                                        viewmodel.selectedColor.value == index
                                    ) {

                                        viewmodel.selectedColor.value = index

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
                                onValueChange = { sliderPosition = it },
                                valueRange = 5f..25f,
                                steps = 5,
                                colors = SliderDefaults.colors(
                                    thumbColor = IgboBlue, activeTrackColor = IgboBlue
                                )
                            )

                        }


                    }

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .padding(bottom = 7.dp, top = 7.dp)
                            .width(250.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = IgboBlue)
                    ) {
                        Text(
                            text = "Save", fontFamily = Util.quicksand,
                        )
                    }

                }

            }
        }

    }

}