package fm.weigl.refsberlin.error.view

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.*
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.Toaster
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ErrorScreenTest {

    val toaster: Toaster = mock()
    val resources: Resources = mock()
    val delegate: ErrorScreenDelegate = mock()

    val classToTest = ErrorScreen(toaster, resources)

    @Before
    fun setUp() {
        classToTest.delegate = delegate
    }

    @Test
    fun setsErrorMessage() {

        val error = "error"
        val errorMessage = "errorMessage"

        given(resources.getString(R.string.error_w_reason, error)).willReturn(errorMessage)

        classToTest.showMajorError(error)

        assertEquals(errorMessage, classToTest.error.get())

    }

    @Test
    fun clearsErrorMessage() {

        given(resources.getString(eq(R.string.error_w_reason), any())).willReturn("error")

        classToTest.showMajorError("error")

        classToTest.hideError()

        assertNull(classToTest.error.get())

    }

    @Test
    fun delegatesRetryClick() {

        classToTest.retryClicked()

        then(delegate).should().retryClicked()

    }

    @Test
    fun showsToastOnMinorError() {

        val error = "error"
        given(resources.getString(R.string.error)).willReturn(error)

        classToTest.showMinorError()

        then(toaster).should().showToast(error)

    }

}