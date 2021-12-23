package com.searcharchitect.two.screen.login.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.common.CommonOutlinedTextField
import com.searcharchitect.two.ui.theme.Blue500
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun LoginCheckingPreview() {
    SearchArchitectTheme {
        LoginChecking()
    }
}


@Composable
fun LoginChecking() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp, top = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Icon(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "App logo",
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.width(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var login by remember { mutableStateOf("") }

        CommonOutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = stringResource(R.string.username),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        var password by remember { mutableStateOf("") }

        CommonOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(30.dp))

        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.login_text),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = stringResource(R.string.psthv_email),
            color = Blue500,
            style = MaterialTheme.typography.body1
        )
    }
}