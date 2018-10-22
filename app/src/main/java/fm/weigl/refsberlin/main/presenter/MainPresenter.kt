package fm.weigl.refsberlin.main.presenter

import android.os.Bundle
import fm.weigl.refsberlin.base.MainLifecycleDelegate
import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.main.view.NavigationDrawerDelegate
import javax.inject.Inject


@ActivityScope
class MainPresenter @Inject constructor(
        private val mainNavigator: MainNavigator
) : NavigationDrawerDelegate, MainLifecycleDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        mainNavigator.showGamesList(onlyIfNothingElseShown = true)
    }

    override fun settingsSelected() =  mainNavigator.showSettings()

    override fun gamesListSelected() = mainNavigator.showGamesList()

    override fun privacySelected() = mainNavigator.showPrivacy()

    override fun aboutTheAppSelected() = mainNavigator.showAboutTheApp()
}