package com.larrex.learnnsibidiradicals

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.larrex.learnnsibidiradicals.ui.navigation.MainNavigation
import com.larrex.learnnsibidiradicals.ui.screens.SplashScreen
import com.larrex.learnnsibidiradicals.ui.theme.LearnNsibidiRadicalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

//        val windowInsetsController =
//            ViewCompat.getWindowInsetsController(window.decorView)
//
//        windowInsetsController?.isAppearanceLightNavigationBars = true

        val isItemSaved = getSharedPreferences("adCount", Context.MODE_PRIVATE)
        val adCount = isItemSaved.getInt("ad", 0)

        val gfgPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(gfgPolicy)

        var mInterstitialAd2: InterstitialAd? = null

        val editPreference = isItemSaved.edit()

        editPreference.putInt("ad", adCount + 1)

        if (adCount > 3){
            editPreference.putInt("ad", 0)
        }

        editPreference.commit()

//        Toast.makeText(this,""+adCount,Toast.LENGTH_LONG).show()
        if (adCount == 3) {

            val adRequest: AdRequest = AdRequest.Builder().build()

            InterstitialAd.load(
                this,
                "ca-app-pub-4141073472139762/5835404399",
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {

                        mInterstitialAd2 = null

                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {

                        mInterstitialAd2 = interstitialAd

                        mInterstitialAd2?.show(this@MainActivity)

                    }
                })

        }

        setContent {
            LearnNsibidiRadicalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    MainNavigation(navHostController = navController)

                }
            }
        }
    }
}
