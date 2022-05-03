package cz.stepanzalis.spacexlifts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import cz.stepanzalis.spacexlifts.SpaceXApp
import cz.stepanzalis.spacexlifts.io.utils.rememberWindowSizeClass
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)

            setContent {
                val windowSizeClass = rememberWindowSizeClass()
                SpaceXApp(windowSizeClass)
            }
        }
    }
}