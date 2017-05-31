package fm.weigl.refsberlin.di

import dagger.Component
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import fm.weigl.refsberlin.main.view.MainActivity

/**
 * Created by asco on 1/31/17.
 */

@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: GamesListFragment)
    fun inject(fragment: AboutTheAppFragment)
}