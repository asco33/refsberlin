package fm.weigl.refsberlin.settings.view

import android.databinding.ObservableBoolean
import javax.inject.Inject

interface SettingsDelegate {
    fun trackingEnabledChanged(enabled: Boolean)
}

interface ISettingsView {
    fun showTrackingEnabled(enabled: Boolean)
}

class SettingsView @Inject constructor() : ISettingsView {

    val trackingEnabled = ObservableBoolean(false)
    lateinit var delegate: SettingsDelegate

    override fun showTrackingEnabled(enabled: Boolean) {
        trackingEnabled.set(enabled)
    }

    fun checkedChanged(checked: Boolean) = delegate.trackingEnabledChanged(checked)
}