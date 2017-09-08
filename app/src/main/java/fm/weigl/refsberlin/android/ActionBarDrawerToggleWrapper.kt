package fm.weigl.refsberlin.android

import android.app.Activity
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import javax.inject.Inject

class ActionBarDrawerToggleWrapper @Inject constructor() {

    fun create(activity: Activity,
               drawer: DrawerLayout,
               toolbar: Toolbar,
               openResId: Int,
               closeResId: Int)

            = ActionBarDrawerToggle(activity,
            drawer,
            toolbar,
            openResId,
            closeResId)

}