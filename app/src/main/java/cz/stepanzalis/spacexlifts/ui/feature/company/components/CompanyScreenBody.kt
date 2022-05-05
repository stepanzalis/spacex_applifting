package cz.stepanzalis.spacexlifts.ui.feature.company.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cz.stepanzalis.spacexlifts.io.common.SpacingM
import cz.stepanzalis.spacexlifts.io.common.SpacingS
import cz.stepanzalis.spacexlifts.ui.base.BaseScreen
import cz.stepanzalis.spacexlifts.ui.feature.company.CompanyVM
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun CompanyScreenBody(
    modifier: Modifier,
    companyVM: CompanyVM = getViewModel(),
) {

    val viewState = companyVM.viewState.collectAsState()

    BaseScreen(
        status = viewState.value.status,
        vm = companyVM,
        showFullscreenLoading = true
    ) {
        Column(modifier = modifier.padding(horizontal = SpacingM)) {
            with(viewState.value) {
                Text(
                    text = company.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = SpacingM),
                )
                CompanyCard(company = company, modifier = Modifier.padding(top = SpacingS))
                Text(text = company.summary, modifier = Modifier.padding(top = SpacingM))
            }
        }
    }
}

@Preview("Company screen body preview")
@Composable
fun CompanyScreenBodyPreview() {
    SpaceXLiftsTheme {
        Surface {
            CompanyScreenBody(modifier = Modifier)
        }
    }
}