package com.searcharchitect.two.screen.info

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun SplashErrorPreview() {
    SearchArchitectTheme {
        InfoScreen(
            version = "v 2.1.5 / 3",
            onClickEmail = {}
        )
    }
}


@Composable
fun InfoScreen(
    version: String,
    onClickEmail: () -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = version,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.if_you_want_change_data),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.psthv_email),
            color = Blue500,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.clickable { onClickEmail() }
        )
    }
}