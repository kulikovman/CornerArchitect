package com.searcharchitect.two.screen.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.searcharchitect.two.R
import com.searcharchitect.two.ui.theme.Amber500
import com.searcharchitect.two.ui.theme.SearchArchitectTheme


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DetailScreenPreview() {
    SearchArchitectTheme {
        DetailScreen(
            onClickBack = {},
            profileImageLink = "https://www.example.com/image.jpg",
            name = "Павел",
            surname = "Серебрянников",
            location = "Челябинск",
            specialization = "Дизайн городской среды / Программирование развития территорий",
            work = "ИП Козак / Агентство Развития Территорий Градостроительная школа",
            position = "Индивидуальный предприниматель, управляющий партнер",
            phone = "+7 (952) 111-22-33",
            email = "ilyakov.arch@gmail.com",
            site = "example.com",
            note = "Какое-то примечание. Все что не попало в остальные поля. Возможно даже длинное. Какое-то примечание. Все что не попало в остальные поля. Возможно даже длинное. Какое-то примечание. Все что не попало в остальные поля. Возможно даже длинное.",
            telegramId = "",
            instagramId = null,
            facebookId = "",
            vkId = "",
            onClickTelegram = {},
            onClickInstagram = {},
            onClickFacebook = {},
            onClickVk = {},
        )
    }
}


@Composable
fun DetailScreen(
    onClickBack: () -> Unit,
    profileImageLink: String?,
    name: String,
    surname: String,
    location: String,
    specialization: String,
    work: String,
    position: String,
    phone: String,
    email: String,
    site: String,
    note: String,

    telegramId: String?,
    instagramId: String?,
    facebookId: String?,
    vkId: String?,
    onClickTelegram: ((String) -> Unit),
    onClickInstagram: ((String) -> Unit),
    onClickFacebook: ((String) -> Unit),
    onClickVk: ((String) -> Unit),
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back button",
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.clickable { onClickBack.invoke() }
        )

        profileImageLink?.let {
            Image(
                painter = rememberImagePainter(
                    data = profileImageLink,
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
            text = location,
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

        Text(
            text = work,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 15.dp)
        )

        Text(
            text = position,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 15.dp)
        )

        Text(
            text = phone,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = email,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 15.dp)
        )

        Text(
            text = site,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row {
            telegramId?.let { id ->
                Icon(
                    painter = painterResource(R.drawable.ic_telegram_48),
                    contentDescription = "telegram",
                    modifier = Modifier
                        .padding(top = 30.dp, end = 20.dp)
                        .clickable { onClickTelegram.invoke(id) }
                )
            }

            instagramId?.let { id ->
                Icon(
                    painter = painterResource(R.drawable.ic_instagram_48),
                    contentDescription = "instagram",
                    modifier = Modifier
                        .padding(top = 30.dp, end = 20.dp)
                        .clickable { onClickInstagram.invoke(id) }
                )
            }

            facebookId?.let { id ->
                Icon(
                    painter = painterResource(R.drawable.ic_facebook_48),
                    contentDescription = "facebook",
                    modifier = Modifier
                        .padding(top = 30.dp, end = 20.dp)
                        .clickable { onClickFacebook.invoke(id) }
                )
            }

            vkId?.let { id ->
                Icon(
                    painter = painterResource(R.drawable.ic_vk_48),
                    contentDescription = "vk",
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clickable { onClickVk.invoke(id) }
                )
            }
        }

        Row(modifier = Modifier.padding(top = 40.dp)) {
            Icon(
                imageVector = Icons.Sharp.Subject,
                contentDescription = "Note icon",
                tint = Amber500,
                modifier = Modifier.clickable { onClickBack.invoke() },
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