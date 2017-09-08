package fm.weigl.refsberlin.error.view

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.then
import fm.weigl.refsberlin.R
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ErrorScreenTest {

    @Mock lateinit var resources: Resources
    @Mock lateinit var delegate: ErrorScreenDelegate

    lateinit var classToTest: ErrorScreen

    @Before
    fun setUp() {
        classToTest = ErrorScreen(resources)
        classToTest.delegate = delegate
    }

    @Test
    fun setsErrorMessage() {

        val error = "error"
        val errorMessage = "errorMessage"

        given(resources.getString(R.string.error, error)).willReturn(errorMessage)

        classToTest.showError(error)

        assertEquals(errorMessage, classToTest.error.get())

    }

    @Test
    fun clearsErrorMessage() {

        given(resources.getString(eq(R.string.error), any())).willReturn("error")

        classToTest.showError("error")

        classToTest.hideError()

        assertNull(classToTest.error.get())

    }

    @Test
    fun delegatesRetryClick() {

        classToTest.retryClicked()

        then(delegate).should().retryClicked()

    }

}