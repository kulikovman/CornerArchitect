package com.searcharchitect.two.screen.search.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.utility.log
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.Gray300
import com.searcharchitect.two.ui.theme.SearchArchitectTheme
import com.searcharchitect.two.utility.SampleData

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ItemSearchContactPreview() {
    SearchArchitectTheme {
        ItemSearchContact(
            contact = SampleData.testContact,
            openDetailScreen = {}
        )
    }
}


@Composable
fun ItemSearchContact(
    contact: Contact,
    openDetailScreen: (contact: Contact) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                log("Click on ${contact.surname} ${contact.name}")
                openDetailScreen(contact)
            }
    ){
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))

            if (contact.photoPreviewLink != null) {
                Image(
                    painter = rememberImagePainter(
                        data = contact.photoPreviewLink,
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
            ) {
                Text(
                    text = "${contact.name} ${contact.surname}",
                    maxLines = 2,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = contact.specialization,
                    maxLines = 3,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "${contact.city} / ${contact.region}",
                    maxLines = 2,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}