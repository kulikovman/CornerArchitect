package com.searcharchitect.two.screen.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.Subject
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.searcharchitect.common.model.Contact
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.Amber500
import com.searcharchitect.two.ui.theme.SearchArchitectTheme
import com.searcharchitect.two.utility.SampleData

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DetailDefaultPreview() {
    SearchArchitectTheme {
        DetailDefault(
            contact = SampleData.testContact,
            onClickPhone = {},
            onClickEmail = {},
            onClickLink = {},
            onClickTelegram = {},
            onClickInstagram = {},
            onClickFacebook = {},
            onClickVk = {},
        )
    }
}


@Composable
fun DetailDefault(
    contact: Contact,
    onClickPhone: (String) -> Unit,
    onClickEmail: (String) -> Unit,
    onClickLink: (String) -> Unit,
    onClickTelegram: (String) -> Unit,
    onClickInstagram: (String) -> Unit,
    onClickFacebook: (String) -> Unit,
    onClickVk: (String) -> Unit
) {
    contact.apply {
        val scroll = rememberScrollState(0)

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(scroll)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            photoMaxLink?.let { photoLink ->
                Image(
                    painter = rememberImagePainter(
                        data = photoLink,
                        builder = {
                            placeholder(R.color.gray_200)
                            error(R.color.red_400)
                        }
                    ),
                    contentDescription = "Profile photo",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )

                Spacer(modifier = Modifier.height(15.dp))
            }

            Text(
                text = name,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h4
            )

            Text(
                text = surname,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "$city / $region",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = specialization,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body1
            )

            work?.let { work ->
                Text(
                    text = work,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 15.dp)
                )
            }

            position?.let { position ->
                Text(
                    text = position,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 15.dp)
                )
            }

            phone?.let { phone ->
                Text(
                    text = phone,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clickable { onClickPhone(phone) }
                )
            }

            email?.let { email ->
                Text(
                    text = email,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .clickable { onClickEmail(email) }
                )
            }

            link?.let { link ->
                Text(
                    text = link,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clickable { onClickLink(link) }
                )
            }

            Row {
                telegram?.let { telegram ->
                    Icon(
                        painter = painterResource(R.drawable.ic_telegram_48),
                        contentDescription = "telegram",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(top = 30.dp, end = 20.dp)
                            .clickable { onClickTelegram(telegram) }
                    )
                }

                instagram?.let { instagram ->
                    Icon(
                        painter = painterResource(R.drawable.ic_instagram_48),
                        contentDescription = "instagram",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(top = 30.dp, end = 20.dp)
                            .clickable { onClickInstagram(instagram) }
                    )
                }

                facebook?.let { facebook ->
                    Icon(
                        painter = painterResource(R.drawable.ic_facebook_48),
                        contentDescription = "facebook",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(top = 30.dp, end = 20.dp)
                            .clickable { onClickFacebook(facebook) }
                    )
                }

                vk?.let { vk ->
                    Icon(
                        painter = painterResource(R.drawable.ic_vk_48),
                        contentDescription = "vk",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .clickable { onClickVk(vk) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            note?.let { note ->
                Row(modifier = Modifier.padding(bottom = 20.dp)) {
                    Icon(
                        imageVector = Icons.Sharp.Subject,
                        contentDescription = "Note icon",
                        tint = Amber500
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = note,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}