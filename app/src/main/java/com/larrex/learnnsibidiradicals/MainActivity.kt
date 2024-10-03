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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.larrex.learnnsibidiradicals.ui.conmponent.OpenAdManagers
import com.larrex.learnnsibidiradicals.ui.navigation.MainNavigation
import com.larrex.learnnsibidiradicals.ui.screens.SplashScreen
import com.larrex.learnnsibidiradicals.ui.theme.LearnNsibidiRadicalsTheme

class MainActivity : ComponentActivity() {

    private lateinit var openAdManagers: OpenAdManagers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        openAdManagers = OpenAdManagers()
        WindowCompat.setDecorFitsSystemWindows(window, false)

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

    override fun onPause() {
        super.onPause()
        openAdManagers.loadAd(this)
    }

    override fun onRestart() {
        super.onRestart()
        openAdManagers.showAdIfAvailable(this)
    }
}
