package fm.weigl.refsberlin.main.presenter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.nhaarman.mockito_kotlin.*
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import fm.weigl.refsberlin.privacy.PrivacyFragment
import org.junit.Before
import org.junit.Test

class MainNavigatorTest {

    val fragmentManager: FragmentManager = mock()
    val transaction: FragmentTransaction = mock()
    val delegate: MainNavigatorDelegate = mock()

    val classToTest = MainNavigator(fragmentManager)

    @Before
    fun setUp() {
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
    fun notShowsGamesListIfSomethingElseAlreadyShown() {

        given(fragmentManager.findFragmentById(R.id.main_content_container)).willReturn(AboutTheAppFragment())

        classToTest.showGamesList(onlyIfNothingElseShown = true)

        then(fragmentManager).should(never()).beginTransaction()
    }

    @Test
    fun showsPrivacy() {

        mockTransAction()

        classToTest.showPrivacy()

        then(transaction).should().replace(eq(R.id.main_content_container), argThat {
            this is PrivacyFragment
        })

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