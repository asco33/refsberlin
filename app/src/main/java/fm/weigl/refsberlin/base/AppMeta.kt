package fm.weigl.refsberlin.base

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import fm.weigl.refsberlin.R
import javax.inject.Inject

class AppMeta @Inject constructor(
        private val resources: Resources,
        private val context: Context
) {

    fun appName() = resources.getString(R.string.app_name)

    fun versionName() = context.packageManager.getPackageInfo(context.packageName, 0).versionName

}