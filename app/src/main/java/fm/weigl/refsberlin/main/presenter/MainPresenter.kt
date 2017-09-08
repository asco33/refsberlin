package fm.weigl.refsberlin.main.presenter

import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.main.view.NavigationDrawerDelegate
import javax.inject.Inject


@ActivityScope
class MainPresenter @Inject constructor(
        private val mainNavigator: MainNavigator
) : NavigationDrawerDelegate {

    fun start() = mainNavigator.showGamesList()

    override fun gamesListSelected() = mainNavigator.showGamesList()

    override fun aboutTheAppSelected() = mainNavigator.showAboutTheApp()
}