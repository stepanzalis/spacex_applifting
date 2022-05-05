package cz.stepanzalis.spacexlifts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import cz.stepanzalis.spacexlifts.SpaceXApp
import cz.stepanzalis.spacexlifts.io.utils.rememberWindowSizeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val windowSizeClass = rememberWindowSizeClass()
            SpaceXApp(windowSizeClass)
        }
    }
}