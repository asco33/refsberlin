package fm.weigl.refsberlin.main.presenter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.nhaarman.mockito_kotlin.*
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainNavigatorTest {

    @Mock lateinit var fragmentManager: FragmentManager
    @Mock lateinit var transaction: FragmentTransaction
    @Mock lateinit var delegate: MainNavigatorDelegate

    lateinit var classToTest: MainNavigator

    @Before
    fun setUp() {
        classToTest = MainNavigator(fragmentManager)
        classToTest.delegate = delegate
    }

    @Test
    fun showsGamesList() {

        mockTransAction()

        classToTest.showGamesList()

        then(transaction).should().replace(eq(R.id.main_content_container), argThat {
            this is GamesListFragment
        })

    }

    @Test
    fun popsBackStackBeforShowingGamesListFragment() {

        mockTransAction()

        given(fragmentManager.backStackEntryCount).willReturn(1)

        classToTest.showGamesList()

        then(fragmentManager).should().popBackStack()

    }

    @Test
    fun notShowsSecondGamesList() {

        given(fragmentManager.findFragmentById(R.id.main_content_container)).willReturn(GamesListFragment())

        classToTest.showGamesList()

        then(fragmentManager).should(never()).beginTransaction()

    }

    @Test
    fun showsAboutTheApp() {

        mockTransAction()

        classToTest.showAboutTheApp()

        then(transaction).should().replace(eq(R.id.main_content_container), argThat {
            this is AboutTheAppFragment
        })

    }

    @Test
    fun addsFragmentToBackStack() {

        mockTransAction()

        classToTest.showAboutTheApp()

        then(transaction).should().addToBackStack(null)

    }

    @Test
    fun notAddsGamesListToBackStack() {

        mockTransAction()

        classToTest.showGamesList()

        then(transaction).should(never()).addToBackStack(any())

    }

    @Test
    fun delegatesNewFragmentMenuId() {

        mockTransAction()

        classToTest.showAboutTheApp()

        then(delegate).should().fragmentChanged(argThat {
            this is AboutTheAppFragment
        })

    }

    @Test
    fun delegatesFragmentOnBackStackChanged() {

        val fragment = AboutTheAppFragment()
        given(fragmentManager.findFragmentById(R.id.main_content_container)).willReturn(fragment)

        verify(fragmentManager).addOnBackStackChangedListener(argThat {
            this.onBackStackChanged()
            true
        })

        then(delegate).should(atLeastOnce()).fragmentChanged(fragment)

    }


    private fun mockTransAction() {
        given(fragmentManager.beginTransaction()).willReturn(transaction)
        given(transaction.replace(any(), any())).willReturn(transaction)
        given(transaction.addToBackStack(null)).willReturn(transaction)
    }


}