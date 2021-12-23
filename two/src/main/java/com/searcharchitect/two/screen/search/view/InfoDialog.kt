package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.Blue500
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun InfoDialogPreview() {
    SearchArchitectTheme {
        InfoDialog(
            version = "v 2.1.5 / 3",
            onClickEmail = {},
            dismiss = {}
        )
    }
}


@Composable
fun InfoDialog(
    version: String,
    onClickEmail: () -> Unit,
    dismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { dismiss() },
        title = {
            Text(
                text = version,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h6
            )
        },
        text = {
            Column() {
                Text(
                    text = stringResource(R.string.if_you_want_change_data),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(R.string.psthv_email),
                    color = Blue500,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.clickable { onClickEmail() }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { dismiss() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.ok),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.button
                )
            }
        }
    )
}