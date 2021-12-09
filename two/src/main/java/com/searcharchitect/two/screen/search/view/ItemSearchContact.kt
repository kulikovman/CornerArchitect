package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.searcharchitect.common.model.Contact
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.Gray300
import com.searcharchitect.two.ui.theme.SearchArchitectTheme

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ItemSearchContactPreview() {
    SearchArchitectTheme {
        ItemSearchContact(
            contact = Contact(
                id = "001",
                login = "35434",
                password = "sbftb55tb",
                surname = "Еловый",
                name = "Иван",
                patronymic = "",
                region = "Челябинская область",
                city = "Якутия",
                specialization = "Дизайн городской среды",
                work = "",
                position = "",
                email = "",
                phone = "",
                telegram = null,
                instagram = null,
                facebook = null,
                vk = null,
                link = null,
                note = "",
                previewLink = null,
                photoLink = null
            )
        )
    }
}


@Composable
fun ItemSearchContact(
    contact: Contact
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        if (contact.previewLink != null) {
            Image(
                painter = rememberImagePainter(
                    data = contact.previewLink,
                    builder = {
                        placeholder(R.color.gray_200)
                        error(R.color.red_400)
                    }
                ),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Gray300)
            )
        }

        Spacer(modifier = Modifier.width(15.dp))

        Column() {
            Text(
                text = "${contact.name} ${contact.surname}",
                maxLines = 2,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1
            )

            Text(
                text = contact.specialization,
                maxLines = 3,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = "${contact.city} / ${contact.region}",
                maxLines = 2,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body2
            )
        }
    }
}