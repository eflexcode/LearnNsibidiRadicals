package com.larrex.learnnsibidiradicals.ui.conmponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdBannerHome(){

    AndroidView(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 5.dp), factory = { context ->

        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = "ca-app-pub-4141073472139762/9821916719"
            loadAd(AdRequest.Builder().build())
        }

    })

}

@Composable
fun AdBannerDraw(){

    AndroidView(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 5.dp), factory = { context ->

        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = "ca-app-pub-4141073472139762/3209241056"
            loadAd(AdRequest.Builder().build())
        }

    })

}