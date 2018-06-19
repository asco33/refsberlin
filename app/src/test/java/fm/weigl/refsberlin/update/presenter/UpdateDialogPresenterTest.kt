package fm.weigl.refsberlin.update.presenter

import com.nhaarman.mockito_kotlin.*
import fm.weigl.refdata.appversion.AppVersion
import fm.weigl.refsberlin.base.AppMeta
import fm.weigl.refsberlin.navigation.GeneralNavigator
import fm.weigl.refsberlin.update.net.AppVersionRepository
import fm.weigl.refsberlin.update.view.IUpdateDialogView
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class UpdateDialogPresenterTest {

    val appVersionRepository: AppVersionRepository = mock()
    val appMeta: AppMeta = mock()
    val navigator: GeneralNavigator = mock()
    val updateView: IUpdateDialogView = mock()

    val classToTest = UpdateDialogPresenter(appVersionRepository, appMeta, navigator)

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        classToTest.updateView = updateView
    }

    @Test
    fun notChecksForNewAppVersionAfterOrientationChange() {

        classToTest.onCreate(mock())

        then(appVersionRepository).should(never()).appVersion()

    }

    @Test
    fun showsUpdateDialogIfNewVersionIsAvailable() {

        val oldVersionCode = 10
        val newVersionCode = 11
        val appVersion = AppVersion(newVersionCode, "", "")

        given(appMeta.versionCode()).willReturn(oldVersionCode)

        given(appVersionRepository.appVersion()).willReturn(Single.just(appVersion))

        classToTest.onCreate(null)

        then(updateView).should().showUpdateDialog(appVersion)


    }

    @Test
    fun notShowsUpdateDialogIfVersionsAreSame() {

        val versionCode = 10
        val appVersion = AppVersion(versionCode, "", "")

        given(appMeta.versionCode()).willReturn(versionCode)

        given(appVersionRepository.appVersion()).willReturn(Single.just(appVersion))

        classToTest.onCreate(null)

        then(updateView).should(never()).showUpdateDialog(any())


    }

    @Test
    fun opensPlaystoreOnUpdateClick() {

        classToTest.updateClicked()

        then(navigator).should().showPlayStorePage()

    }

}