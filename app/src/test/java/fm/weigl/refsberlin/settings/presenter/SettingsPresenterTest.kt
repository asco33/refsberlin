package fm.weigl.refsberlin.settings.presenter

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import fm.weigl.refsberlin.settings.data.TrackingSettings
import fm.weigl.refsberlin.settings.view.ISettingsView
import org.junit.Before
import org.junit.Test

class SettingsPresenterTest {

    val settings: TrackingSettings = mock()
    val view: ISettingsView = mock()
    val classToTest = SettingsPresenter(settings)

    @Before
    fun setUp() {
        classToTest.view = view
    }

    @Test
    fun updatesViewOnStart() {

        given(settings.isTrackingEnabled()).willReturn(true)

        classToTest.start()

        then(view).should().showTrackingEnabled(true)

    }

    @Test
    fun updatesViewAfterSettingsChanged() {

        given(settings.isTrackingEnabled()).willReturn(false)

        classToTest.trackingEnabledChanged(false)

        then(view).should().showTrackingEnabled(false)

    }

    @Test
    fun updatesSettingsWhenTrackingEnabledChanged() {

        classToTest.trackingEnabledChanged(true)

        then(settings).should().setTrackingEnabled(true)

    }

}