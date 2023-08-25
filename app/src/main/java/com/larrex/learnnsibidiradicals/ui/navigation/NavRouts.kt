package com.larrex.learnnsibidiradicals.ui.navigation

sealed class NavRouts (val rout: String){

    object SplashRout : NavRouts("Splash")

    object HomeRout : NavRouts("Home")

}
