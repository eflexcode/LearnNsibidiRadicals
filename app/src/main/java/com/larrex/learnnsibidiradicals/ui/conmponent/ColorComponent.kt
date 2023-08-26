package com.larrex.learnnsibidiradicals.ui.conmponent;

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.larrex.learnnsibidiradicals.R

@Composable
fun ColorComponent(color: Color, selected: Boolean, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(color, RoundedCornerShape(50.dp))
            .clickable(
//                interactionSource = MutableInteractionSource(),
//                indication = null
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        if (selected) {
            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.check_mark),
                contentDescription = "",
                Modifier.size(30.dp)
            )
        }

    }

}
