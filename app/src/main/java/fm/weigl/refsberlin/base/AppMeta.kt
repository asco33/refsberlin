package fm.weigl.refsberlin.base

import android.content.Context
import fm.weigl.refsberlin.R
import javax.inject.Inject

class AppMeta @Inject constructor(
        context: Context
) {

    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val resources = context.resources

    fun appName() = resources.getString(R.string.app_name)

    fun versionName() = packageInfo.versionName

    fun versionCode() = packageInfo.versionCode

}