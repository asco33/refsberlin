package fm.weigl.refsberlin.tracking

import com.crashlytics.android.answers.Answers
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.then
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrackerTest {

    @Mock lateinit var answers: Answers

    lateinit var classToTest: Tracker

    @Before
    fun setUp() {
        classToTest = Tracker(answers)
    }

    @Test
    fun tracksAppStart() {

        classToTest.trackAppStart()

        then(answers).should().logCustom(any())

    }

}