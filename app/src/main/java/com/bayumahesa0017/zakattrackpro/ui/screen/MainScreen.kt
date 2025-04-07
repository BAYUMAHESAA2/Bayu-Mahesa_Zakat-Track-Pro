package com.bayumahesa0017.zakattrackpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bayumahesa0017.zakattrackpro.R
import com.bayumahesa0017.zakattrackpro.navigation.Screen
import com.bayumahesa0017.zakattrackpro.ui.theme.ZakatTrackProTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController : NavHostController){
    Scaffold (
        topBar ={
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = MaterialTheme.colorScheme.errorContainer
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AboutScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about_screen),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier){
    Column (
        modifier = modifier.
        fillMaxSize().
        padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.payzakat),
            contentDescription = stringResource(R.string.home_screen),
            contentScale = ContentScale.Crop
        )

        Row (modifier = Modifier.fillMaxWidth().padding(16.dp)){
            ElevatedButton(onClick = {}, modifier = Modifier.weight(1f).padding(end = 10.dp), colors = ButtonDefaults.buttonColors(
                Color.Gray)) {
                Text(
                    text = "Zakat fitrah"
                )
            }
            ElevatedButton(onClick = {}, modifier = Modifier.weight(1f).padding(start = 10.dp), colors = ButtonDefaults.buttonColors(
                Color.Gray)) {
            Text(
                text = "Zakat Profesi"
            )
        }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    ZakatTrackProTheme {
        MainScreen(rememberNavController())
    }
}