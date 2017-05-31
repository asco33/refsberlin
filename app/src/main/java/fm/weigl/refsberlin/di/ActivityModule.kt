package fm.weigl.refsberlin.di

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.base.BaseActivity

/**
 * Created by asco on 1/31/17.
 */

@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides
    @ActivityScope
    fun context(): Context = activity

    @Provides
    @ActivityScope
    fun activity(): Activity = activity

    @Provides
    @ActivityScope
    fun baseActivity(): BaseActivity = activity

    @Provides
    @ActivityScope
    fun fragmentManager(): FragmentManager = activity.supportFragmentManager
}