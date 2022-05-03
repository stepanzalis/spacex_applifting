package cz.stepanzalis.spacexlifts.ui.main.feature.company.route

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import cz.stepanzalis.spacexlifts.ui.main.feature.company.CompanyScreen
import cz.stepanzalis.spacexlifts.ui.main.feature.company.CompanyVM

@Composable
fun CompanyRoute(
    companyVM: CompanyVM,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    CompanyScreen(
        companyVM = companyVM,
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}