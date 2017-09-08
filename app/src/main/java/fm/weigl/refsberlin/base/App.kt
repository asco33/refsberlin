package fm.weigl.refsberlin.base

import android.app.Application
import com.crashlytics.android.Crashlytics
import fm.weigl.refsberlin.di.AppComponent
import fm.weigl.refsberlin.di.AppModule
import fm.weigl.refsberlin.di.DaggerAppComponent
import fm.weigl.refsberlin.tracking.Tracker
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

class App : Application() {

    private lateinit var appComponent: AppComponent

    @Inject lateinit var tracker: Tracker

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()

        appComponent.injectTo(this)

        tracker.trackAppStart()

    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

}