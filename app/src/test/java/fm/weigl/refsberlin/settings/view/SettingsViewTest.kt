package fm.weigl.refsberlin.settings.view

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SettingsViewTest {

    val delegate: SettingsDelegate = mock()
    val classToTest = SettingsView()

    @Before
    fun setUp() {
        classToTest.delegate = delegate
    }

    @Test
    fun showsTrackingEnabled() {

        classToTest.showTrackingEnabled(true)

        assertTrue(classToTest.trackingEnabled.get())

        classToTest.showTrackingEnabled(false)

        assertFalse(classToTest.trackingEnabled.get())
    }


    @Test
    fun delegatesCheckedChanged() {

        classToTest.checkedChanged(false)

        then(delegate).should().trackingEnabledChanged(false)

        classToTest.checkedChanged(true)

        then(delegate).should().trackingEnabledChanged(true)
    }

}