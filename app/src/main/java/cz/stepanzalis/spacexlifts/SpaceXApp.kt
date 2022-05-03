package cz.stepanzalis.spacexlifts

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import cz.stepanzalis.spacexlifts.io.utils.WindowSize
import cz.stepanzalis.spacexlifts.ui.base.AppDrawer
import cz.stepanzalis.spacexlifts.ui.base.navigation.SpaceXNavGraph
import cz.stepanzalis.spacexlifts.ui.base.navigation.SpaceXNavigation
import cz.stepanzalis.spacexlifts.ui.base.navigation.SpaceXNavigationAction
import cz.stepanzalis.spacexlifts.ui.theme.LiftingBlack
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme
import kotlinx.coroutines.launch

@Composable
fun SpaceXApp(windowSize: WindowSize) {
    SpaceXLiftsTheme {
        InitSystemUI()

        val navController = rememberNavController()
        val navigationActions = remember(navController) { SpaceXNavigationAction(navController) }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: SpaceXNavigation.Launches

        val isExpandedScreen = windowSize == WindowSize.Expanded
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

        ModalDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToLaunches = navigationActions.navigateToLaunches,
                    navigateToCompany = navigationActions.navigateToCompany,
                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } },
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                )
            },
            drawerState = sizeAwareDrawerState,
            gesturesEnabled = !isExpandedScreen
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .windowInsetsPadding(
                        WindowInsets
                            .navigationBars
                            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                    )
            ) {
                SpaceXNavGraph(
                    isExpandedScreen = isExpandedScreen,
                    navController = navController,
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                )
            }
        }
    }
}

@Composable
private fun InitSystemUI() {
    val systemUiController = rememberSystemUiController()
    val darkIcons = MaterialTheme.colors.isLight
    val isDarkTheme = isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            if (isDarkTheme) LiftingBlack else Color.Transparent,
            darkIcons = darkIcons,
        )
    }
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}