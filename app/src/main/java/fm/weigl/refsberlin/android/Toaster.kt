package fm.weigl.refsberlin.android

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import javax.inject.Inject


/**
 * Created by asco on 6/10/17.
 */

class Toaster @Inject constructor(
        private val context: Context
) {

    private var toast: Toast? = null

    fun showToast(text: String) {
        toast?.cancel()
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast?.setText(text)
        toast?.setGravity(Gravity.TOP or Gravity.RIGHT, 50, 200)
        toast?.show()
    }
}