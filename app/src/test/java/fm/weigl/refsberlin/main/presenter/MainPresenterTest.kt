package fm.weigl.refsberlin.main.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Test

class MainPresenterTest {

    val navigator: MainNavigator = mock()

    val classToTest = MainPresenter(navigator)

    @Test
    fun showsGamesListAtStart() {

        classToTest.onCreate(null)

        then(navigator).should().showGamesList(true)

    }

    @Test
    fun showsGamesListWhenSelected() {

        classToTest.gamesListSelected()

        then(navigator).should().showGamesList(false)

    }

    @Test
    fun showPrivacy() {

        classToTest.privacySelected()

        then(navigator).should().showPrivacy()

    }

    @Test
    fun showsAboutTheApp() {

        classToTest.aboutTheAppSelected()

        then(navigator).should().showAboutTheApp()

    }

}