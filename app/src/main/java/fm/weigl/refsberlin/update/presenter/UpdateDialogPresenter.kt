package fm.weigl.refsberlin.update.presenter

import android.os.Bundle
import fm.weigl.refdata.appversion.AppVersion
import fm.weigl.refsberlin.base.AppMeta
import fm.weigl.refsberlin.base.MainLifecycleDelegate
import fm.weigl.refsberlin.navigation.GeneralNavigator
import fm.weigl.refsberlin.rx.Schedulers
import fm.weigl.refsberlin.update.net.AppVersionRepository
import fm.weigl.refsberlin.update.view.IUpdateDialogView
import fm.weigl.refsberlin.update.view.UpdateDialogDelegate
import javax.inject.Inject

class UpdateDialogPresenter @Inject constructor(
        private val appVersionRepository: AppVersionRepository,
        private val appMeta: AppMeta,
        private val navigator: GeneralNavigator,
        private val schedulers: Schedulers
) : MainLifecycleDelegate, UpdateDialogDelegate {

    lateinit var updateView: IUpdateDialogView

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) return

        appVersionRepository.appVersion()
                .subscribeOn(schedulers.new())
                .observeOn(schedulers.main())
                .subscribe(
                        {
                            appVersionLoaded(it)
                        }, {
                            it.printStackTrace()
                        }
                )
    }

    override fun updateClicked() = navigator.showPlayStorePage()

    private fun appVersionLoaded(appVersion: AppVersion) {

        if (appVersion.versionCode > appMeta.versionCode()) updateView.showUpdateDialog(appVersion)

    }
}