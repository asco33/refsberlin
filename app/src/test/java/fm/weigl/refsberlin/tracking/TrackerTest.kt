package fm.weigl.refsberlin.tracking

import com.crashlytics.android.answers.Answers
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

class TrackerTest {

    val answers: Answers = mock()

    val classToTest = Tracker(answers)

    @Test
    fun tracksAppStart() {

        classToTest.trackAppStart()

        then(answers).should().logCustom(any())

    }
}