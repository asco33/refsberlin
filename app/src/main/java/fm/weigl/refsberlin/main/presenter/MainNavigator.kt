package fm.weigl.refsberlin.main.presenter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.base.ExtraLifecycleDelegate
import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import javax.inject.Inject

interface IMainNavigator {
    fun showGamesList()
    fun showAboutTheApp()
}

@ActivityScope
class MainNavigator @Inject constructor(
        private val fragmentManager: FragmentManager
) : IMainNavigator, ExtraLifecycleDelegate {

    override fun showGamesList() {
        if (!isGamesListFragmentOnTop()) {
            showFragment(GamesListFragment())
        }
    }

    override fun showAboutTheApp() = showFragment(AboutTheAppFragment())

    override fun onBackPressed(): Boolean {

        if (fragmentManager.backStackEntryCount == 0) return false

        fragmentManager.popBackStack()

        return true
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_content_container, fragment)
                .addToBackStack(null)
                .commit()
    }

    private fun isGamesListFragmentOnTop() = fragmentManager.fragments?.firstOrNull() is GamesListFragment
}