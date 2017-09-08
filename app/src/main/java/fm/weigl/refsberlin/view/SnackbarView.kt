package fm.weigl.refsberlin.view

import android.support.design.widget.Snackbar
import android.view.View
import javax.inject.Inject

interface ISnackbarView {
    fun showSnackbar(text: String)
}

class SnackbarView @Inject constructor() : ISnackbarView {

    var parentView: View? = null

    override fun showSnackbar(text: String) {
        parentView?.apply {
            Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
        }

    }
}