package fm.weigl.refsberlin.base

import android.app.Activity
import fm.weigl.refsberlin.di.ActivityScope
import javax.inject.Inject

/**
 * Created by asco on 4/19/17.
 */

@ActivityScope
class UINavigator @Inject constructor(
        private val activity: Activity,
        private val intentProvider: IntentProvider
) {

    fun createCalendarEventForGame(startTime: Long, endTime: Long, eventName: String, eventDescription: String, location: String)
            = activity.startActivity(intentProvider.createCalendarEventIntent(startTime, endTime, eventName, eventDescription, location))

    fun showNavigationToLocation(location: String) = activity.startActivity(intentProvider.showNavigationIntent(location))

}