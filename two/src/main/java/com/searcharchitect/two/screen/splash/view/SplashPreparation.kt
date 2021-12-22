package com.searcharchitect.two.screen.splash.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
fun SplashPreparationPreview() {
    SearchArchitectTheme {
        SplashPreparation()
    }
}


@Composable
fun SplashPreparation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "App logo",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.width(140.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.preparation_for_launch),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(20.dp))

        CircularProgressIndicator()
    }
}