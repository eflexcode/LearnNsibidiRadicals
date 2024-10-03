package com.larrex.learnnsibidiradicals.ui.conmponent

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.larrex.learnnsibidiradicals.MainActivity
import java.util.Date
import kotlin.math.log

class OpenAdManagers {

    val OpenAdID = "ca-app-pub-6649871470282634/2389262790"
    private val TAG = "OpenAdManagers"

    private var loadTime: Long = 0
    var openAd: AppOpenAd? = null
    private var isAdLoading = false
    var isAdShowing = false

    fun loadAd(context: Context) {
        if (isAdLoading || isAdAvailable()) {
            return
        }
        isAdLoading = true

        var request = AdRequest.Builder().build()

        AppOpenAd.load(
            context,
            OpenAdID,
            request,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    Log.d(TAG, p0.message)
                    isAdLoading = false

                }

                override fun onAdLoaded(p0: AppOpenAd) {
                    super.onAdLoaded(p0)
                    Log.d(TAG, "onAdLoaded: Load ad successful")
                    openAd = p0
                    isAdLoading = false
                    loadTime = Date().time
                }
            }
        )
    }

    private fun wasLoadTimeLessThanHourAgo(numHour: Long): Boolean {
        val dateDifference: Long = Date().time - loadTime
        val numMilliSecondPerHour: Long = 3_600_000
        return dateDifference < numMilliSecondPerHour * numHour
    }

    private fun isAdAvailable(): Boolean {
        return openAd != null && wasLoadTimeLessThanHourAgo(4)
    }

    fun showAdIfAvailable(mainActivity: MainActivity){
        if (isAdShowing){
            return
        }

        if (!isAdAvailable()){
            loadAd(mainActivity)
        }
        openAd?.fullScreenContentCallback = object :FullScreenContentCallback(){
            override fun onAdDismissedFullScreenContent() {
                openAd = null
                isAdShowing = false
                loadAd(mainActivity)
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                openAd = null
                isAdShowing = false
                loadAd(mainActivity)
            }
        }
        isAdShowing = true
        openAd?.show(mainActivity)
    }
}