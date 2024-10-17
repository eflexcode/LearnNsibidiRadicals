package com.larrex.learnnsibidiradicals.ui.conmponent

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.BannerView.IListener
import com.unity3d.services.banners.UnityBannerSize


@Composable
fun AdBannerHome(){

    AndroidView(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 45.dp, bottom = 2.dp), factory = { context ->

        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = "ca-app-pub-6649871470282634/2389262790"
            loadAd(AdRequest.Builder().build())
        }

    })

}

@Composable
fun AdBannerDraw(){

    AndroidView(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 10.dp), factory = { context ->

        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = "ca-app-pub-6649871470282634/8759230026"
            loadAd(AdRequest.Builder().build())
        }

    })

}
@Composable
fun AdBannerHomeUnity(activity: Activity){

    AndroidView(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, bottom = 2.dp), factory = { context ->

     val bannerView = BannerView(activity, "Banner_Android", UnityBannerSize(350, 50))
        bannerView
        loadUnityAd(bannerView)


    })



}

fun loadUnityAd(bannerView: BannerView): View {
    bannerView.load()

    return bannerView
}

private val bannerListener: IListener = object : IListener {
    override fun onBannerLoaded(bannerAdView: BannerView) {
        // Called when the banner is loaded.
        Log.v("UnityAdsExample", "onBannerLoaded: " + bannerAdView.placementId)
        // Enable the correct button to hide the ad
//        (if (bannerAdView.placementId == "topBanner") hideTopBannerButton else hideBottomBannerButton).setEnabled(
//            true
//        )
    }

    override fun onBannerFailedToLoad(bannerAdView: BannerView, errorInfo: BannerErrorInfo) {
        Log.e(
            "UnityAdsExample",
            "Unity Ads failed to load banner for " + bannerAdView.placementId + " with error: [" + errorInfo.errorCode + "] " + errorInfo.errorMessage
        )
        // Note that the BannerErrorInfo object can indicate a no fill (refer to the API documentation).
    }

    override fun onBannerClick(bannerAdView: BannerView) {
        // Called when a banner is clicked.
        Log.v("UnityAdsExample", "onBannerClick: " + bannerAdView.placementId)
    }

    override fun onBannerLeftApplication(bannerAdView: BannerView) {
        // Called when the banner links out of the application.
        Log.v("UnityAdsExample", "onBannerLeftApplication: " + bannerAdView.placementId)
    }
}

