package fm.weigl.refsberlin.update.presenter

import com.nhaarman.mockito_kotlin.*
import fm.weigl.refdata.appversion.AppVersion
import fm.weigl.refsberlin.base.AppMeta
import fm.weigl.refsberlin.rx.TestSchedulers
import fm.weigl.refsberlin.update.net.AppVersionRepository
import fm.weigl.refsberlin.update.view.IUpdateDialogView
import org.junit.Before
import org.junit.Test
import rx.Observable

class UpdateDialogPresenterTest {

    val appVersionRepository: AppVersionRepository = mock()
    val appMeta: AppMeta = mock()
    val schedulers = TestSchedulers()
    val updateView: IUpdateDialogView = mock()

    val classToTest = UpdateDialogPresenter(appVersionRepository, appMeta, schedulers)

    @Before
    fun setUp() {
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

        given(appVersionRepository.appVersion()).willReturn(Observable.just(appVersion))

        classToTest.onCreate(null)

        then(updateView).should().showUpdateDialog(appVersion)


    }

    @Test
    fun notShowsUpdateDialogIfVersionsAreSame() {

        val versionCode = 10
        val appVersion = AppVersion(versionCode, "", "")

        given(appMeta.versionCode()).willReturn(versionCode)

        given(appVersionRepository.appVersion()).willReturn(Observable.just(appVersion))

        classToTest.onCreate(null)

        then(updateView).should(never()).showUpdateDialog(any())


    }

}