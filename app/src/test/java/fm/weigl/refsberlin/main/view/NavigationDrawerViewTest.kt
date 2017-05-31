package fm.weigl.refsberlin.main.view

import android.app.Activity
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.then
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.ActionBarDrawerToggleWrapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by asco on 5/23/17.
 */

@RunWith(MockitoJUnitRunner::class)
class NavigationDrawerViewTest {

    @Mock lateinit var activity: Activity
    @Mock lateinit var toggleWrapper: ActionBarDrawerToggleWrapper
    @Mock lateinit var toggle: ActionBarDrawerToggle
    @Mock lateinit var drawer: DrawerLayout
    @Mock lateinit var navigationView: NavigationView
    @Mock lateinit var toolbar: Toolbar
    @Mock lateinit var delegate: NavigationDrawerDelegate
    @Mock lateinit var menuItem: MenuItem

    lateinit var classToTest: NavigationDrawerView

    @Before
    fun setUp() {
        classToTest = NavigationDrawerView(activity, toggleWrapper)
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
    fun delegatesAboutTheAppSelected() {

        given(menuItem.itemId).willReturn(R.id.nav_about_the_app)

        classToTest.onNavigationItemSelected(menuItem)

        then(delegate).should().aboutTheAppSelected()

    }

}