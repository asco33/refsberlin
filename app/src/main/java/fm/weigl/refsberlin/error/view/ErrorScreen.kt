package fm.weigl.refsberlin.error.view

import android.content.res.Resources
import android.databinding.ObservableField
import fm.weigl.refsberlin.R
import javax.inject.Inject


interface IErrorScreen {
    fun showError(error: String)
    fun hideError()
}

interface ErrorScreenDelegate {
    fun retryClicked()
}

class ErrorScreen @Inject constructor(
        private val resources: Resources
) : IErrorScreen {

    val error = ObservableField<String>()

    var delegate: ErrorScreenDelegate? = null

    fun retryClicked() = delegate?.retryClicked()

    override fun showError(error: String) = this.error.set(resources.getString(R.string.error, error))

    override fun hideError() = error.set(null)
}