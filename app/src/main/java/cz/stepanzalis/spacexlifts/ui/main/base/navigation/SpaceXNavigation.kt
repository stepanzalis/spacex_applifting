package cz.stepanzalis.spacexlifts.ui.main.base.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Destinations used in the [SpaceXDestinations].
 */
object SpaceXNavigation {
    const val Launches = "launches"
    const val Company = "company"
}

/**
 * Models the navigation actions in the app.
 */
class SpaceXNavigationAction(navController: NavHostController) {
    val navigateToLaunches: () -> Unit = {
        navController.navigate(SpaceXNavigation.Launches) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToCompany: () -> Unit = {
        navController.navigate(SpaceXNavigation.Company) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
