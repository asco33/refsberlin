package fm.weigl.refsberlin.settings.presenter

import fm.weigl.refsberlin.settings.data.TrackingSettings
import fm.weigl.refsberlin.settings.view.ISettingsView
import fm.weigl.refsberlin.settings.view.SettingsDelegate
import javax.inject.Inject

class SettingsPresenter @Inject constructor(private val settings: TrackingSettings) : SettingsDelegate {

    lateinit var view: ISettingsView

    fun start() {
        updateView()
    }

    override fun trackingEnabledChanged(enabled: Boolean) {
        settings.setTrackingEnabled(enabled)
        updateView()
    }

    private fun updateView() = view.showTrackingEnabled(settings.isTrackingEnabled())
}