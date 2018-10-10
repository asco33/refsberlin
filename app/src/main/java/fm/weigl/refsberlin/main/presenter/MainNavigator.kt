package fm.weigl.refsberlin.main.presenter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import fm.weigl.refsberlin.privacy.PrivacyFragment
import javax.inject.Inject

interface IMainNavigator {
    fun showGamesList(onlyIfNothingElseShown: Boolean = false)
    fun showPrivacy()
    fun showAboutTheApp()
}

interface MainNavigatorDelegate {
    fun fragmentChanged(fragment: Fragment)
}

@ActivityScope
class MainNavigator @Inject constructor(
        private val fragmentManager: FragmentManager
) : IMainNavigator {

    var delegate: MainNavigatorDelegate? = null

    init {
        fragmentManager.addOnBackStackChangedListener {

            currentFragment()?.apply {
                delegate?.fragmentChanged(this)
            }
        }
    }

    override fun showGamesList(onlyIfNothingElseShown: Boolean) {

        if (onlyIfNothingElseShown && !nothingShown()) return

        if (isGamesListFragmentOnTop()) return

        clearBackStackToShowGamesList()
        showFragment(GamesListFragment(), false)
    }

    override fun showPrivacy() {
        if (!isPrivacyFragmentOnTop()) {
            showFragment(PrivacyFragment())
        }
    }

    override fun showAboutTheApp() {
        if (!isAboutTheAppFragmentOnTop()) {
            showFragment(AboutTheAppFragment())
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {

        var replace = fragmentManager
                .beginTransaction()
                .replace(R.id.main_content_container, fragment)

        if (addToBackStack) replace = replace.addToBackStack(null)

        replace.commit()

        delegate?.fragmentChanged(fragment)
    }

    private fun nothingShown() = currentFragment() == null

    private fun currentFragment(): Fragment? = fragmentManager.findFragmentById(R.id.main_content_container)

    private fun isGamesListFragmentOnTop() = currentFragment() is GamesListFragment

    private fun isPrivacyFragmentOnTop() = currentFragment() is PrivacyFragment

    private fun isAboutTheAppFragmentOnTop() = currentFragment() is AboutTheAppFragment

    private fun clearBackStackToShowGamesList() {

        if (fragmentManager.backStackEntryCount > 0) {
            (1..fragmentManager.backStackEntryCount).forEach {
                fragmentManager.popBackStack()
            }
        }
    }
}