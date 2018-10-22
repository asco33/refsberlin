package fm.weigl.refsberlin.settings.data

import android.content.SharedPreferences
import javax.inject.Inject

class TrackingSettings @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        private const val KEY_TRACKING_ENABLED = "KEY_TRACKING_ENABLED"
    }

    fun setTrackingEnabled(enabled: Boolean) = prefs.edit().putBoolean(KEY_TRACKING_ENABLED, enabled).commit()

    fun isTrackingEnabled() = prefs.getBoolean(KEY_TRACKING_ENABLED, true)

}