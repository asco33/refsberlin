package fm.weigl.refsberlin.base

import android.support.multidex.MultiDexApplication
import com.crashlytics.android.core.CrashlyticsCore
import fm.weigl.refsberlin.di.AppComponent
import fm.weigl.refsberlin.di.AppModule
import fm.weigl.refsberlin.di.DaggerAppComponent
import fm.weigl.refsberlin.settings.data.TrackingSettings
import fm.weigl.refsberlin.tracking.Tracker
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

class App : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var tracker: Tracker
    @Inject
    lateinit var trackingSettings: TrackingSettings

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

        appComponent.injectTo(this)

        val trackingEnabled = trackingSettings.isTrackingEnabled()
        val crashlytics = CrashlyticsCore.Builder().disabled(!trackingEnabled).build()
        Fabric.with(this, crashlytics)

        tracker.trackAppStart()

    }

}