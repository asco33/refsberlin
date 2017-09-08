package fm.weigl.refsberlin.base

import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import javax.inject.Inject

class IntentProvider @Inject constructor() {

    fun createCalendarEventIntent(startTime: Long,
                                  endTime: Long,
                                  eventName: String,
                                  eventDescription: String,
                                  location: String): Intent {
        val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                .putExtra(CalendarContract.Events.TITLE, eventName)
                .putExtra(Events.DESCRIPTION, eventDescription)
                .putExtra(Events.EVENT_LOCATION, location)

        return intent
    }

    fun showNavigationIntent(location: String): Intent {
        return Intent(android.content.Intent.ACTION_VIEW,
                      Uri.parse("http://maps.google.com/maps?daddr=$location"))
    }

}