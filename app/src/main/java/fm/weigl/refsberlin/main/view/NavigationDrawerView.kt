package fm.weigl.refsberlin.main.view

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.ActionBarDrawerToggleWrapper
import fm.weigl.refsberlin.base.ExtraLifecycleDelegate
import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.main.presenter.FragmentMenuMapper
import fm.weigl.refsberlin.main.presenter.MainNavigatorDelegate
import javax.inject.Inject

interface NavigationDrawerDelegate {
    fun gamesListSelected()
    fun aboutTheAppSelected()
}

@ActivityScope
class NavigationDrawerView @Inject constructor(
        private val activity: Activity,
        private val toggleWrapper: ActionBarDrawerToggleWrapper,
        private val menuMapper: FragmentMenuMapper
) : NavigationView.OnNavigationItemSelectedListener, MainNavigatorDelegate, ExtraLifecycleDelegate {

    lateinit var drawer: DrawerLayout
    lateinit var navigationView: NavigationView

    var delegate: NavigationDrawerDelegate? = null

    private val allMenuIds = listOf(R.id.nav_gameslist, R.id.nav_about_the_app)


    fun setViews(drawer: DrawerLayout, navigationView: NavigationView, toolbar: Toolbar) {
        this.drawer = drawer
        this.navigationView = navigationView
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

    override fun fragmentChanged(fragment: Fragment) {

        val menuIdForFragment = menuMapper.menuIdForFragment(fragment)

        allMenuIds.forEach {
            navigationView.menu.findItem(it)?.isChecked = it == menuIdForFragment
        }
    }

    override fun onBackPressed(): Boolean {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return true
        }
        return false

    }
}