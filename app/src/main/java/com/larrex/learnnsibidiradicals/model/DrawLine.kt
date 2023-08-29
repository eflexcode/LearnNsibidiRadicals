package com.larrex.learnnsibidiradicals.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DrawLine(
    var color: Color = Color.Black,
    var strokeWidth: Dp = 5.dp,
    val start: Offset,
    val end: Offset
) {
}