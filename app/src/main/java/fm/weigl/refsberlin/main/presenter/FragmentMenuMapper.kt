package fm.weigl.refsberlin.main.presenter

import android.support.v4.app.Fragment
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import javax.inject.Inject

/**
 * Created by asco on 6/8/17.
 */
class FragmentMenuMapper @Inject constructor() {

    fun menuIdForFragment(fragment: Fragment): Int {

        return when (fragment) {
            is GamesListFragment -> R.id.nav_gameslist
            is AboutTheAppFragment -> R.id.nav_about_the_app
            else -> -1
        }
    }
}