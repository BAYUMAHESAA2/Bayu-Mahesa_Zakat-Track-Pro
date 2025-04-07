package com.bayumahesa0017.zakattrackpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bayumahesa0017.zakattrackpro.navigation.SetUpNavGraph
import com.bayumahesa0017.zakattrackpro.ui.theme.ZakatTrackProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZakatTrackProTheme {
               SetUpNavGraph()
            }
        }
    }
}
