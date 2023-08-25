package com.larrex.learnnsibidiradicals.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.larrex.learnnsibidiradicals.ui.screens.HomeScreen
import com.larrex.learnnsibidiradicals.ui.screens.SplashScreen

@Composable
fun MainNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = NavRouts.SplashRout.rout) {

        composable(NavRouts.SplashRout.rout){
            SplashScreen(navController = navHostController)
        }

        composable(NavRouts.HomeRout.rout){
            HomeScreen(navController = navHostController)
        }

    }

}