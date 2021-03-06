package fm.weigl.refsberlin.main.view

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.ActionBarDrawerToggleWrapper
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import fm.weigl.refsberlin.main.presenter.FragmentMenuMapper
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NavigationDrawerViewTest {

    val activity: Activity = mock()
    val toggleWrapper: ActionBarDrawerToggleWrapper = mock()
    val toggle: ActionBarDrawerToggle = mock()
    val drawer: DrawerLayout = mock()
    val navigationView: NavigationView = mock()
    val toolbar: Toolbar = mock()
    val delegate: NavigationDrawerDelegate = mock()
    val menuItem: MenuItem = mock()
    val menuMapper: FragmentMenuMapper = mock()

    val classToTest = NavigationDrawerView(activity, toggleWrapper, menuMapper)

    @Before
    fun setUp() {
        classToTest.delegate = delegate
        given(toggleWrapper.create(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close))
                .willReturn(toggle)
        classToTest.setViews(drawer, navigationView, toolbar)
    }

    @Test
    fun delegatesGamesListSelected() {

        given(menuItem.itemId).willReturn(R.id.nav_gameslist)

        classToTest.onNavigationItemSelected(menuItem)

        then(delegate).should().gamesListSelected()

    }

    @Test
    fun delegatesSettingsSelected() {

        given(menuItem.itemId).willReturn(R.id.nav_settings)

        classToTest.onNavigationItemSelected(menuItem)

        then(delegate).should().settingsSelected()

    }

    @Test
    fun delegatesAboutTheAppSelected() {

        given(menuItem.itemId).willReturn(R.id.nav_about_the_app)

        classToTest.onNavigationItemSelected(menuItem)

        then(delegate).should().aboutTheAppSelected()

    }

    @Test
    fun delegatesPrivacySelected() {

        given(menuItem.itemId).willReturn(R.id.nav_privacy)

        classToTest.onNavigationItemSelected(menuItem)

        then(delegate).should().privacySelected()

    }

    @Test
    fun closesDrawerOnBackPressed() {

        given(drawer.isDrawerOpen(GravityCompat.START)).willReturn(true)

        classToTest.onBackPressed()

        then(drawer).should().closeDrawer(GravityCompat.START)

    }

    @Test
    fun handlesBackPressedWhenDrawerIsOpen() {

        given(drawer.isDrawerOpen(GravityCompat.START)).willReturn(true)

        assertTrue(classToTest.onBackPressed())

    }

    @Test
    fun notHandlesBackPressedWhenDrawerIsClosed() {

        given(drawer.isDrawerOpen(GravityCompat.START)).willReturn(false)

        assertFalse(classToTest.onBackPressed())

    }

    @Test
    fun updatesMenuItemsChecked() {

        val fragment = GamesListFragment()
        given(menuMapper.menuIdForFragment(fragment)).willReturn(R.id.nav_gameslist)

        val gamesListMenuItem = mock<MenuItem>()
        val settingsMenuItem = mock<MenuItem>()
        val aboutTheAppMenuItem = mock<MenuItem>()
        val privacyMenuItem = mock<MenuItem>()

        val menu = mock<Menu>()
        given(navigationView.menu).willReturn(menu)

        given(menu.findItem(R.id.nav_gameslist)).willReturn(gamesListMenuItem)
        given(menu.findItem(R.id.nav_settings)).willReturn(settingsMenuItem)
        given(menu.findItem(R.id.nav_privacy)).willReturn(privacyMenuItem)
        given(menu.findItem(R.id.nav_about_the_app)).willReturn(aboutTheAppMenuItem)

        classToTest.fragmentChanged(fragment)

        then(gamesListMenuItem).should().isChecked = true
        then(settingsMenuItem).should().isChecked = false
        then(privacyMenuItem).should().isChecked = false
        then(aboutTheAppMenuItem).should().isChecked = false
    }
}