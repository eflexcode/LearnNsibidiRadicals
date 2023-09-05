package com.larrex.learnnsibidiradicals.ui.conmponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.larrex.learnnsibidiradicals.R
import com.larrex.learnnsibidiradicals.Util
import com.larrex.learnnsibidiradicals.model.NsibidiItemModel
import com.larrex.learnnsibidiradicals.ui.navigation.NavRouts

@Composable
fun NsibidiItem(
    navController: NavController,
    nsibidiItemModel: NsibidiItemModel,
    onClick: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(130.dp)
            .padding(10.dp)
            .clickable {

                onClick()

            },
        elevation = CardDefaults.cardElevation(10.dp),
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = nsibidiItemModel.imageId),
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Inside,
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

}