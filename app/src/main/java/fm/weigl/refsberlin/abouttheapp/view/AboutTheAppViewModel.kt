package fm.weigl.refsberlin.abouttheapp.view

import android.databinding.ObservableField
import fm.weigl.refsberlin.base.AppMeta
import javax.inject.Inject

class AboutTheAppViewModel @Inject constructor(
        appMeta: AppMeta
) {
    val appNameAndVersion = ObservableField<String>("")

    init {
        appNameAndVersion.set("${appMeta.appName()} ${appMeta.versionName()}")
    }

}