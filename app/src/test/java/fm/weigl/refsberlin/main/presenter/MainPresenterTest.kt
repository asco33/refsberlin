package fm.weigl.refsberlin.main.presenter

import com.nhaarman.mockito_kotlin.then
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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

        then(navigator).should().showGamesList(true)

    }

    @Test
    fun showsGamesListWhenSelected() {

        classToTest.gamesListSelected()

        then(navigator).should().showGamesList(false)

    }

    @Test
    fun showsAboutTheApp() {

        classToTest.aboutTheAppSelected()

        then(navigator).should().showAboutTheApp()

    }

}