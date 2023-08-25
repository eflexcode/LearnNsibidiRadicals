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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.Util
import com.larrex.learnnsibidiradicals.ui.conmponent.ColorComponent
import com.larrex.learnnsibidiradicals.ui.theme.Black
import com.larrex.learnnsibidiradicals.ui.theme.Black2
import com.larrex.learnnsibidiradicals.ui.theme.Blue
import com.larrex.learnnsibidiradicals.ui.theme.Green
import com.larrex.learnnsibidiradicals.ui.theme.Orange
import com.larrex.learnnsibidiradicals.ui.theme.Pink
import com.larrex.learnnsibidiradicals.ui.theme.Purple
import com.larrex.learnnsibidiradicals.ui.theme.White
import com.larrex.learnnsibidiradicals.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color.Transparent, true)

    var showSheet by remember {
        mutableStateOf(false)
    }
    val controller = rememberColorPickerController()

    val supportedColors =
        listOf<Color>(Black, Blue, Orange, Yellow, Green, Purple, Pink, Black2, White)

    val supportedThickness =
        listOf<Float>(6f, 10f, 18f, 24f)

    var rangeColor by remember {
        mutableStateOf(Color.White)
    }

    var sliderPosition by remember {
        mutableStateOf(0.5f)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {

        Scaffold(floatingActionButton = {

            ExtendedFloatingActionButton(onClick = {
                showSheet = true
            },
                text = { Text(text = "Settings") },
                icon = { Icon(Icons.Default.Settings, contentDescription = "") })


        }) { paddingContent ->
            paddingContent
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showSheet = false
                    },
                    sheetState = rememberModalBottomSheetState(false), containerColor = Color.White
                ) {

                    Column(
                        modifier = Modifier.background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Colors \uD83C\uDFA8",
                                modifier = Modifier.padding(start = 20.dp, bottom = 7.dp),
                                fontWeight = FontWeight.Bold
                            )

                            LazyRow(
                                content = {

                                    items(supportedColors) {

                                        ColorComponent(color = it, true)
                                        Spacer(modifier = Modifier.width(15.dp))

                                    }
                                },
                                contentPadding = PaddingValues(start = 30.dp, end = 30.dp),
                                modifier = Modifier.padding(bottom = 7.dp)
                            )

                            Text(
                                text = "Brush thickness \uD83D\uDD8C",
                                modifier = Modifier.padding(start = 20.dp),
                                fontWeight = FontWeight.Bold
                            )

                            Column( modifier = Modifier.padding(bottom = 7.dp, start = 40.dp,end = 40.dp)) {
                                Canvas(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(26.dp), onDraw = {

                                        drawLine(
                                            color = Black,
                                            strokeWidth = sliderPosition*15f,
                                            cap = StrokeCap.Round,
                                            start = Offset(x = 0f, y = size.height),
                                            end = Offset(
                                                x = size.width,
                                                y = size.height
                                            )
                                        )
                                    })

                                Slider(value = sliderPosition, onValueChange = {sliderPosition = it})

                            }



                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(bottom = 7.dp, top = 7.dp)
                                .width(250.dp)
                        ) {
                            Text(text = "Save")
                        }

                    }

                }
            }

        }

    }

}