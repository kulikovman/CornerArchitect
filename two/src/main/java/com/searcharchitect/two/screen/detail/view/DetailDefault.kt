package com.searcharchitect.two.screen.detail.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            onClickBack = {},
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
    onClickBack: () -> Unit,
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

        Column(modifier = Modifier
            .padding(20.dp)
            .verticalScroll(scroll)
        ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back button",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.clickable { onClickBack.invoke() }
                )

                photoLink?.let { photoLink ->
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
                            .height(200.dp)
                            .padding(top = 20.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }

                Text(
                    text = name,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 15.dp)
                )

                Text(
                    text = surname,
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h4
                )

                Text(
                    text = "$city / $region",
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 15.dp)
                )

                Text(
                    text = specialization,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 15.dp)
                )

                work?.let { work ->
                    Text(
                        text = work,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }

                position?.let { position ->
                    Text(
                        text = position,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                }

                phone?.let { phone ->
                    Text(
                        text = phone,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .clickable { onClickPhone(phone) }
                    )
                }

                email?.let { email ->
                    Text(
                        text = email,
                        color = MaterialTheme.colors.onSurface,
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
                            modifier = Modifier
                                .padding(top = 30.dp, end = 20.dp)
                                .clickable { onClickTelegram(telegram) }
                        )
                    }

                    instagram?.let { instagram ->
                        Icon(
                            painter = painterResource(R.drawable.ic_instagram_48),
                            contentDescription = "instagram",
                            modifier = Modifier
                                .padding(top = 30.dp, end = 20.dp)
                                .clickable { onClickInstagram(instagram) }
                        )
                    }

                    facebook?.let { facebook ->
                        Icon(
                            painter = painterResource(R.drawable.ic_facebook_48),
                            contentDescription = "facebook",
                            modifier = Modifier
                                .padding(top = 30.dp, end = 20.dp)
                                .clickable { onClickFacebook(facebook) }
                        )
                    }

                    vk?.let { vk ->
                        Icon(
                            painter = painterResource(R.drawable.ic_vk_48),
                            contentDescription = "vk",
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .clickable { onClickVk(vk) }
                        )
                    }
                }

                note?.let { note ->
                    Row(modifier = Modifier.padding(top = 40.dp)) {
                        Icon(
                            imageVector = Icons.Sharp.Subject,
                            contentDescription = "Note icon",
                            tint = Amber500,
                            modifier = Modifier.clickable { onClickBack() },
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = note,
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
}