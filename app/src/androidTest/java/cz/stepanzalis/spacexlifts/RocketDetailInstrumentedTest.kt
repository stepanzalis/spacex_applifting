package cz.stepanzalis.spacexlifts

import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cz.stepanzalis.spacexlifts.io.common.AppBarTestTag
import cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail.RocketDetailScreen
import cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail.RocketDetailVM
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class RocketDetailInstrumentedTest : KoinTest {

    private val viewModel by inject<RocketDetailVM> { parametersOf("") }

    private lateinit var navHostController: TestNavHostController

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        navHostController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            SpaceXLiftsTheme {
                Surface {
                    RocketDetailScreen(
                        id = "",
                        navController = navHostController,
                        rocketVM = viewModel,
                        scaffoldState = rememberScaffoldState(),
                    )
                }
            }
        }
    }

    @Test
    fun top_bar_shows() {
        composeTestRule.onNodeWithTag(AppBarTestTag).assertIsDisplayed()
    }
}