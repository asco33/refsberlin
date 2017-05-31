package fm.weigl.refsberlin.main.view

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.ActionBarDrawerToggleWrapper
import fm.weigl.refsberlin.di.ActivityScope
import javax.inject.Inject

interface NavigationDrawerDelegate {
    fun gamesListSelected()
    fun aboutTheAppSelected()
}

@ActivityScope
class NavigationDrawerView @Inject constructor(
        private val activity: Activity,
        private val toggleWrapper: ActionBarDrawerToggleWrapper
) : NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawer: DrawerLayout

    var delegate: NavigationDrawerDelegate? = null


    fun setViews(drawer: DrawerLayout, navigationView: NavigationView, toolbar: Toolbar) {
        this.drawer = drawer
        val toggle = toggleWrapper.create(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_gameslist -> delegate?.gamesListSelected()
            R.id.nav_about_the_app -> delegate?.aboutTheAppSelected()
        }

        drawer.closeDrawers()

        return true
    }
}