package com.searcharchitect.common.utility.extension

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.getAppVersion(withHash: Boolean = false): String? {
    return packageManager?.getPackageInfo(this.packageName, 0)?.versionName.let { version ->
        if (withHash) {
            version
        } else {
            version?.split(".")?.dropLast(1)?.joinToString(".")
        }
    }
}

fun Context.sendEmail(email: String) {
    val uri = Uri.parse("mailto:$email")
    val intent = Intent(Intent.ACTION_SENDTO, uri)

    startActivity(Intent.createChooser(intent, null))
}

fun Context.callPhoneNumber(phone: String) {
    val uri: Uri = Uri.parse("tel:$phone")
    val callIntent = Intent(Intent.ACTION_DIAL, uri)

    startActivity(callIntent)
}

fun Context.openTelegram(profileId: String) {
    val appUri = Uri.parse("https://t.me/$profileId")
    val appIntent = Intent(Intent.ACTION_VIEW, appUri).apply {
        `package` = "org.telegram.messenger"
    }

    val browserUri = Uri.parse("https://t.me/$profileId")
    val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)

    try {
        startActivity(appIntent)
    } catch (e: ActivityNotFoundException) {
        startActivity(browserIntent)
    }
}

fun Context.openInstagram(profileId: String) {
    val appUri = Uri.parse("http://instagram.com/_u/$profileId")
    val appIntent = Intent(Intent.ACTION_VIEW, appUri).apply {
        component = ComponentName(
            "com.instagram.android",
            "com.instagram.android.activity.UrlHandlerActivity"
        )
    }

    val browserUri = Uri.parse("http://instagram.com/$profileId")
    val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)

    try {
        startActivity(appIntent)
    } catch (e: ActivityNotFoundException) {
        startActivity(browserIntent)
    }
}

fun Context.openFacebook(profileId: String) {
    val uri = Uri.parse("https://www.facebook.com/n/?$profileId")
    val intent = Intent(Intent.ACTION_VIEW, uri)

    startActivity(intent)
}

fun Context.openVk(profileId: String) {
    val uri = Uri.parse("http://vk.com/$profileId")
    val intent = Intent(Intent.ACTION_VIEW, uri)

    startActivity(intent)
}

fun Context.openLink(link: String) {
    val correctLink = if (!link.startsWith("http://")
        || !link.startsWith("https://")
    ) "http://$link" else link

    val uri = Uri.parse(correctLink)
    val intent = Intent(Intent.ACTION_VIEW, uri)

    startActivity(intent)
}