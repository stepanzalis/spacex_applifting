package cz.stepanzalis.spacexlifts.ui.main.feature.company

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.DrawerIconHeight
import cz.stepanzalis.spacexlifts.io.utils.ext.showDrawerIcon

@Composable
fun CompanyScreen(
    companyVM: CompanyVM,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.company_title),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                },
                navigationIcon = if (!isExpandedScreen) {
                    {
                        IconButton(onClick = openDrawer) {
                            Icon(
                                painter = painterResource(isExpandedScreen.showDrawerIcon()),
                                contentDescription = stringResource(R.string.core_drawer_open),
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.heightIn(max = DrawerIconHeight)
                            )
                        }
                    }
                } else null,
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        CompanyScreenBody(companyVM, isExpandedScreen, screenModifier)
    }
}