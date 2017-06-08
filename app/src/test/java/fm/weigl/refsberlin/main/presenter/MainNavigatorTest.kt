package fm.weigl.refsberlin.main.presenter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.nhaarman.mockito_kotlin.*
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.abouttheapp.view.AboutTheAppFragment
import fm.weigl.refsberlin.gameslist.view.GamesListFragment
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by asco on 5/24/17.
 */

@RunWith(MockitoJUnitRunner::class)
class MainNavigatorTest {

    @Mock lateinit var fragmentManager: FragmentManager
    @Mock lateinit var transaction: FragmentTransaction

    lateinit var classToTest: MainNavigator

    @Before
    fun setUp() {
        classToTest = MainNavigator(fragmentManager)
    }

    @Test
    fun notHandlesBackPressedWhenBackstackIsEmpty() {

        given(fragmentManager.backStackEntryCount).willReturn(0)

        assertFalse(classToTest.onBackPressed())

    }

    @Test
    fun handlesBackPressedWhenBackstackIsNotEmpty() {

        given(fragmentManager.backStackEntryCount).willReturn(1)

        assertTrue(classToTest.onBackPressed())

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
    fun notShowsSecondGamesList() {

        given(fragmentManager.fragments).willReturn(listOf(GamesListFragment()))

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


    private fun mockTransAction() {
        given(fragmentManager.beginTransaction()).willReturn(transaction)
        given(transaction.replace(any(), any())).willReturn(transaction)
        given(transaction.addToBackStack(null)).willReturn(transaction)
    }


}