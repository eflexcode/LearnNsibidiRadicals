package com.larrex.learnnsibidiradicals.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.larrex.learnnsibidiradicals.ui.screens.DrawingScreen
import com.larrex.learnnsibidiradicals.ui.screens.HomeScreen
import com.larrex.learnnsibidiradicals.ui.screens.SplashScreen
import com.larrex.learnnsibidiradicals.model.NsibidiItemModel


@Composable
fun MainNavigation(navHostController: NavHostController) {
//    val navController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavRouts.SplashRout.rout) {

        composable(NavRouts.SplashRout.rout) {
            SplashScreen(navController = navHostController)
        }

        composable(NavRouts.DrawingRout.rout) {

            val nsibidiItemModel =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<NsibidiItemModel>("imageclass")

            if (nsibidiItemModel != null) {
                DrawingScreen(
                    navController = navHostController,
                    nsibidiItemModel = nsibidiItemModel
                )
            }
        }

        composable(NavRouts.HomeRout.rout) {
            HomeScreen(navController = navHostController)
        }

    }

}