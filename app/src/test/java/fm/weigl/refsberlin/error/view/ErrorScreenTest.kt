package fm.weigl.refsberlin.error.view

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.*
import fm.weigl.refsberlin.R
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ErrorScreenTest {

    val resources: Resources = mock()
    val delegate: ErrorScreenDelegate = mock()

    val classToTest = ErrorScreen(resources)

    @Before
    fun setUp() {
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