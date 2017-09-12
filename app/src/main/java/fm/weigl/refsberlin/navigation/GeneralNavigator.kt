package fm.weigl.refsberlin.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

class GeneralNavigator @Inject constructor(
        private val activity: Activity
) {

    companion object {
        const val DETAILS = "details?id=fm.weigl.refsberlin"
        const val DIRECT_URL = "market://$DETAILS"
        const val PLAYSTORE_URL = "https://play.google.com/store/apps/$DETAILS"
    }

    fun showPlayStorePage() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(DIRECT_URL)
        try {
            activity.startActivity(intent)
        } catch (e: Exception) {
            intent.data = Uri.parse(PLAYSTORE_URL)
            activity.startActivity(intent)
        }
    }

}