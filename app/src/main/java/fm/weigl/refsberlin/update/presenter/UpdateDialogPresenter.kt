package fm.weigl.refsberlin.update.presenter

import android.os.Bundle
import fm.weigl.refdata.appversion.AppVersion
import fm.weigl.refsberlin.base.AppMeta
import fm.weigl.refsberlin.base.MainLifecycleDelegate
import fm.weigl.refsberlin.navigation.GeneralNavigator
import fm.weigl.refsberlin.update.net.AppVersionRepository
import fm.weigl.refsberlin.update.view.IUpdateDialogView
import fm.weigl.refsberlin.update.view.UpdateDialogDelegate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpdateDialogPresenter @Inject constructor(
        private val appVersionRepository: AppVersionRepository,
        private val appMeta: AppMeta,
        private val navigator: GeneralNavigator
) : MainLifecycleDelegate, UpdateDialogDelegate {

    lateinit var updateView: IUpdateDialogView

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) return

        appVersionRepository.appVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            appVersionLoaded(it)
                        }, {
                    it.printStackTrace()
                })
    }

    override fun updateClicked() = navigator.showPlayStorePage()

    private fun appVersionLoaded(appVersion: AppVersion) {

        if (appVersion.versionCode > appMeta.versionCode()) updateView.showUpdateDialog(appVersion)

    }
}