package com.larrex.learnnsibidiradicals.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.larrex.learnnsibidiradicals.model.DrawLine

class MainViewModel : ViewModel() {

    var selectedColor = mutableStateOf(0)
    val drawLineList = mutableStateListOf<DrawLine>()

}