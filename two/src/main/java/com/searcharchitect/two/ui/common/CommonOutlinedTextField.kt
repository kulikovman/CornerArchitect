package com.searcharchitect.two.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun CommonOutlinedTextFieldPreview1() {
    SearchArchitectTheme {
        CommonOutlinedTextField(
            value = "Text for example",
            onValueChange = {},
            label = "Label",
            leadingIcon = Icons.Filled.LocationCity,
            trailingIcon = Icons.Outlined.Clear
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun CommonOutlinedTextFieldPreview2() {
    SearchArchitectTheme {
        CommonOutlinedTextField(
            value = "Text for example",
            onValueChange = {},
            label = "Label"
        )
    }
}


@Composable
fun CommonOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = null,
    textFieldColors: TextFieldColors? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            label?.let {
                Text(label)
            }
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = if (leadingIcon == null) null else {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Leading icon"
                )
            }
        },
        trailingIcon = if (trailingIcon == null) null else {
            {
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onClickTrailingIcon?.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "Trailing icon"
                        )
                    }
                }
            }
        },
        keyboardOptions = keyboardOptions ?: KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        colors = textFieldColors ?: TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onBackground
        ),
        visualTransformation = visualTransformation
    )
}