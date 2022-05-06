package cz.stepanzalis.spacexlifts.ui.feature.company.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cz.stepanzalis.spacexlifts.ui.base.StatusScreen
import cz.stepanzalis.spacexlifts.ui.feature.company.CompanyVM
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme
import cz.stepanzalis.spacexlifts.ui.theme.spacing
import org.koin.androidx.compose.getViewModel

@Composable
fun CompanyScreenBody(
    modifier: Modifier,
    companyVM: CompanyVM = getViewModel(),
) {

    val viewState = companyVM.viewState.collectAsState()

    StatusScreen(
        vm = companyVM,
        showFullscreenLoading = true
    ) {
        Column(modifier = modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
            with(viewState.value) {
                Text(
                    text = company.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
                )
                CompanyCard(
                    company = company,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small)
                )
                Text(
                    text = company.summary,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.medium)
                )
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