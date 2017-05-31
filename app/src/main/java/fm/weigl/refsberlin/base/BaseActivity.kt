package fm.weigl.refsberlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fm.weigl.refsberlin.di.ActivityComponent
import fm.weigl.refsberlin.di.ActivityModule
import fm.weigl.refsberlin.di.DaggerActivityComponent

/**
 * Created by asco on 15.07.16.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    private fun appComponent() = (applicationContext as App).getAppComponent()
    private var extraLifecycleDelegates = emptyList<ExtraLifecycleDelegate>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder().appComponent(appComponent()).activityModule(ActivityModule(this)).build()
    }


    protected fun setExtraLifecycleDelegates(vararg delegates: ExtraLifecycleDelegate) {
        extraLifecycleDelegates = delegates.toList()
    }

    override fun onBackPressed() {

        extraLifecycleDelegates.forEach {

            if (it.onBackPressed()) return

        }

        super.onBackPressed()

    }

    override fun onDestroy() {
        super.onDestroy()

        if (isChangingConfigurations) extraLifecycleDelegates = emptyList()
    }

}