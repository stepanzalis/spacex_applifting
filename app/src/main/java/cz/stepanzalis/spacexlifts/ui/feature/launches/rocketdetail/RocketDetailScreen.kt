package cz.stepanzalis.spacexlifts.ui.feature.launches.rocketdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.IconHeight
import cz.stepanzalis.spacexlifts.ui.theme.AppBarElevation
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RocketDetailScreen(
    id: String,
    navController: NavController,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    rocketVM: RocketDetailVM = getViewModel { parametersOf(id) },
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier.height(100.dp),
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.rocket_detail_title),
                            textAlign = TextAlign.Left
                        )
                        Text(
                            text = stringResource(R.string.rocket_detail_title),

                            textAlign = TextAlign.Left
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navController::popBackStack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.core_back),
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.heightIn(max = IconHeight)
                        )}
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = AppBarElevation
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        RocketDetailBody(rocketVM, screenModifier)
    }
}