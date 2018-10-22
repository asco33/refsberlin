package fm.weigl.refsberlin.tracking

import com.crashlytics.android.answers.Answers
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

class TrackerTest {

    val answersProvider: AnswersProvider = mock()
    val answers: Answers = mock()

    val classToTest = Tracker(answersProvider)

    @Test
    fun tracksAppStart() {

        given(answersProvider.answers()).willReturn(answers)

        classToTest.trackAppStart()

        then(answers).should().logCustom(any())

    }
}