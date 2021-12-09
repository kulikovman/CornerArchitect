package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.SearchArchitectTheme
import com.searcharchitect.two.ui.theme.Teal400

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchProgressPreview() {
    SearchArchitectTheme {
        SearchProgress(
            locationText = "",
            specializationText = "",
            nameText = "",
            onClickInfo = {}
        )
    }
}


@Composable
fun SearchProgress(
    locationText: String,
    specializationText: String,
    nameText: String,
    onClickInfo: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            elevation = 4.dp,
            color = Teal400,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                var location by remember { mutableStateOf(locationText) }
                var specialization by remember { mutableStateOf(specializationText) }
                var name by remember { mutableStateOf(nameText) }

                TextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text(stringResource(R.string.location)) },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = specialization,
                    onValueChange = { specialization = it },
                    enabled = false,
                    label = { Text(stringResource(R.string.specialization)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    enabled = false,
                    label = { Text(stringResource(R.string.name)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Info icon",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .clickable { onClickInfo.invoke() }
                        .align(Alignment.End),
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}