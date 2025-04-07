package com.bayumahesa0017.zakattrackpro.navigation

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
    data object ZakatFitrah : Screen("zakatfitrah")
    data object AboutScreen : Screen("AboutScreen")
}