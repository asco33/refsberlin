package fm.weigl.refsberlin.tracking

import com.crashlytics.android.answers.Answers
import fm.weigl.refsberlin.settings.data.TrackingSettings
import javax.inject.Inject

class AnswersProvider @Inject constructor(private val trackingSettings: TrackingSettings) {

    fun answers() = if (trackingSettings.isTrackingEnabled()) Answers.getInstance() else null

}