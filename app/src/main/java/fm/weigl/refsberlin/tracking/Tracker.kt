package fm.weigl.refsberlin.tracking

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import javax.inject.Inject

class Tracker @Inject constructor(private val answers: Answers) {

    companion object {
        const val EVENT_APP_START = "AppStart"
    }

    fun trackAppStart() = answers.logCustom(CustomEvent(EVENT_APP_START))

}
