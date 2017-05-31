package fm.weigl.refsberlin.main.presenter

import com.nhaarman.mockito_kotlin.then
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by asco on 5/24/17.
 */

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock lateinit var navigator: MainNavigator
    lateinit var classToTest: MainPresenter

    @Before
    fun setUp() {
        classToTest = MainPresenter(navigator)
    }

    @Test
    fun showsGamesListAtStart() {

        classToTest.start()

        then(navigator).should().showGamesList()

    }

    @Test
    fun showsGamesList() {

        classToTest.gamesListSelected()

        then(navigator).should().showGamesList()

    }

    @Test
    fun showsAboutTheApp() {

        classToTest.aboutTheAppSelected()

        then(navigator).should().showAboutTheApp()

    }

}