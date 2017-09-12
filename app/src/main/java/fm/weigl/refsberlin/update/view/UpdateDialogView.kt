package fm.weigl.refsberlin.update.view

import fm.weigl.refdata.appversion.AppVersion
import javax.inject.Inject

interface IUpdateDialogView {
    fun showUpdateDialog(appVersion: AppVersion)
}

class UpdateDialogView @Inject constructor() : IUpdateDialogView {
    override fun showUpdateDialog(appVersion: AppVersion) {

    }
}