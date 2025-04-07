package com.bayumahesa0017.zakattrackpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bayumahesa0017.zakattrackpro.ui.screen.AboutScreen
import com.bayumahesa0017.zakattrackpro.ui.screen.MainScreen


@Composable
fun SetUpNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable (route = Screen.Home.route){
            MainScreen(navController)
        }
        composable (route = Screen.AboutScreen.route){
            AboutScreen(navController)
        }
    }
}