package com.searcharchitect.one.utility.extension

import android.content.Context

fun Context.getAppVersion(withHash: Boolean = false): String? {
    return packageManager?.getPackageInfo(this.packageName, 0)?.versionName.let { version ->
        if (withHash) {
            version
        } else {
            version?.split(".")?.dropLast(1)?.joinToString(".")
        }
    }
}