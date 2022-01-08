package com.searcharchitect.two.screen.splash.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SplashErrorPreview() {
    SearchArchitectTheme {
        SplashError(
            onClickTryAgain = {}
        )
    }
}


@Composable
fun SplashError(
    onClickTryAgain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.CloudOff,
            contentDescription = "Icon",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = stringResource(R.string.loading_error_text),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onClickTryAgain
        ) {
            Text(
                text = stringResource(R.string.try_again),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1
            )
        }
    }
}