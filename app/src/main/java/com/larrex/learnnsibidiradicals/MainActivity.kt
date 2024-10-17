package com.larrex.learnnsibidiradicals

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.ads.MobileAds
import com.larrex.learnnsibidiradicals.broadcastreceiver.SendDailyAlarm
import com.larrex.learnnsibidiradicals.ui.conmponent.OpenAdManagers
import com.larrex.learnnsibidiradicals.ui.navigation.MainNavigation
import com.larrex.learnnsibidiradicals.ui.theme.LearnNsibidiRadicalsTheme
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAds.UnityAdsLoadError
import com.unity3d.ads.UnityAds.UnityAdsShowCompletionState
import com.unity3d.ads.UnityAds.UnityAdsShowError
import com.unity3d.ads.UnityAdsShowOptions
import java.util.Calendar


class MainActivity : ComponentActivity(), IUnityAdsInitializationListener {

    private lateinit var openAdManagers: OpenAdManagers

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        UnityAds.initialize(getApplicationContext(), "5712179", false, this);
        openAdManagers = OpenAdManagers()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    7
                );
            }

        }

        setContent {
            LearnNsibidiRadicalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                        val permissionList =
                            listOf(Manifest.permission.POST_NOTIFICATIONS)


                        val storagePermission = rememberMultiplePermissionsState(permissionList)
                    }

                    val navController = rememberNavController()
                    MainNavigation(navHostController = navController)

                }
            }
        }


        val intent = Intent(this, SendDailyAlarm::class.java)

        var pendingIntent: PendingIntent? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pendingIntent =
                PendingIntent.getBroadcast(this, 34, intent, PendingIntent.FLAG_IMMUTABLE)

        } else {
            pendingIntent =
                PendingIntent.getBroadcast(this, 34, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager


//        val pendingIntent =
//            PendingIntent.getBroadcast(this, 34, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 7
        calendar[Calendar.MINUTE] = 30

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    override fun onPause() {
        super.onPause()
        openAdManagers.loadAd(this)
    }

    override fun onRestart() {
        super.onRestart()
        openAdManagers.showAdIfAvailable(this)
    }

    private val loadListener: IUnityAdsLoadListener = object : IUnityAdsLoadListener {
        override fun onUnityAdsAdLoaded(placementId: String) {
            UnityAds.show(
                applicationContext as Activity,
                "video",
                UnityAdsShowOptions(),
                showListener
            )
        }

        override fun onUnityAdsFailedToLoad(
            placementId: String,
            error: UnityAdsLoadError,
            message: String
        ) {
            Log.e(
                "UnityAdsExample",
                "Unity Ads failed to load ad for $placementId with error: [$error] $message"
            )
        }
    }

    private val showListener: IUnityAdsShowListener = object : IUnityAdsShowListener {
        override fun onUnityAdsShowFailure(
            placementId: String,
            error: UnityAdsShowError,
            message: String
        ) {
            Log.e(
                "UnityAdsExample",
                "Unity Ads failed to show ad for $placementId with error: [$error] $message"
            )
        }

        override fun onUnityAdsShowStart(placementId: String) {
            Log.v("UnityAdsExample", "onUnityAdsShowStart: $placementId")
        }

        override fun onUnityAdsShowClick(placementId: String) {
            Log.v("UnityAdsExample", "onUnityAdsShowClick: $placementId")
        }

        override fun onUnityAdsShowComplete(
            placementId: String,
            state: UnityAdsShowCompletionState
        ) {
            Log.v("UnityAdsExample", "onUnityAdsShowComplete: $placementId")
        }
    }


    override fun onInitializationComplete() {
        DisplayInterstitialAd();
        println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo")
    }

    override fun onInitializationFailed(
        error: UnityAds.UnityAdsInitializationError?,
        message: String?
    ) {
    }
    private fun DisplayInterstitialAd() {
        UnityAds.load("video", loadListener)
    }

}
