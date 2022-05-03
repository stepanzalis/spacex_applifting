package cz.stepanzalis.spacexlifts.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.stepanzalis.spacexlifts.AppDebugToolsConfig
import cz.stepanzalis.spacexlifts.R
import cz.stepanzalis.spacexlifts.io.base.BaseVM
import cz.stepanzalis.spacexlifts.io.base.Status

@Composable
fun BaseScreen(
    status: Status,
    vm: BaseVM,
    showFullscreenLoading: Boolean = false,
    content: @Composable () -> Unit,
) {

    when (status) {
        is Status.Loading -> {
            if (showFullscreenLoading) {
                LoadingIndicator()
            }
        }
        is Status.Failure -> {
            AppDebugToolsConfig.logFailure(status)

            ErrorDialog(
                failure = status,
                onDismiss = { vm.dismissErrorDialog() },
            )
        }
        else -> Unit
    }

    content()
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)

    )
    {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorDialog(
    failure: Status.Failure,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        title = { Text(text = stringResource(R.string.error_dialog_title)) },
        text = { Text(failure.toString()) },
        confirmButton = {},
        onDismissRequest = { },
        dismissButton = {
            Button(
                onClick = { onDismiss.invoke() }) {
                Text(text = stringResource(id = R.string.dialog_close))
            }
        }
    )
}
