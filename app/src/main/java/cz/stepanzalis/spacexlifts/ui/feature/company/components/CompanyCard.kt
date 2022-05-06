package cz.stepanzalis.spacexlifts.ui.feature.company.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.common.CardElevation
import cz.stepanzalis.spacexlifts.io.models.company.CompanyVo
import cz.stepanzalis.spacexlifts.ui.theme.LiftingInverted
import cz.stepanzalis.spacexlifts.ui.theme.Shapes
import cz.stepanzalis.spacexlifts.ui.theme.SpaceXLiftsTheme
import cz.stepanzalis.spacexlifts.ui.theme.spacing

@Composable
fun CompanyCard(
    company: CompanyVo,
    modifier: Modifier,
) {
    Card(
        shape = Shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.small),
        elevation = CardElevation,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Column {
                TitleDescription(
                    title = stringResource(R.string.company_address),
                ) {
                    Text(
                        stringResource(
                            id = R.string.company_address_city_placeholder,
                            formatArgs = arrayOf(company.address, company.city)
                        ),
                    )
                }
                Spacer(modifier = Modifier.padding(top = MaterialTheme.spacing.medium))
                TitleDescription(
                    title = stringResource(R.string.company_head),
                ) {
                    Column {
                        Text(company.address)
                        Text(company.city)
                    }
                }
            }
        }
    }
}

@Composable
fun TitleDescription(
    title: String,
    content: @Composable () -> Unit,
) {

    Column {
        Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = LiftingInverted)
        Spacer(modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall))
        content()
    }
}

@Preview("Title with description")
@Composable
fun TitleDescriptionPreview() {
    SpaceXLiftsTheme {
        Surface {
            TitleDescription(title = "Title") {
                Text(text = "Message")
            }
        }
    }
}

@Preview("Company card - empty")
@Composable
fun CompanyCardPreview() {
    SpaceXLiftsTheme {
        Surface {
            CompanyCard(
                company = CompanyVo.initial(),
                modifier = Modifier,
            )
        }
    }
}


