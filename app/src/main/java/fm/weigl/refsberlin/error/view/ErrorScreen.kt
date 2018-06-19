package fm.weigl.refsberlin.error.view

import android.content.res.Resources
import android.databinding.ObservableField
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.Toaster
import javax.inject.Inject


interface IErrorScreen {
    fun showMajorError(error: String)
    fun showMinorError()
    fun hideError()
}

interface ErrorScreenDelegate {
    fun retryClicked()
}

class ErrorScreen @Inject constructor(
        private val toaster: Toaster,
        private val resources: Resources
) : IErrorScreen {

    val error = ObservableField<String>()

    var delegate: ErrorScreenDelegate? = null

    fun retryClicked() = delegate?.retryClicked()

    override fun showMajorError(error: String) = this.error.set(resources.getString(R.string.error_w_reason, error))

    override fun showMinorError() = toaster.showToast(resources.getString(R.string.error))

    override fun hideError() = error.set(null)
}