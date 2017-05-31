package fm.weigl.refsberlin.gameslist.view

import android.app.Activity
import fm.weigl.refsberlin.TestGames
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by asco on 4/20/17.
 */

@RunWith(MockitoJUnitRunner::class)
class GamesListViewTest {

    @Mock lateinit var activity: Activity
    @Mock lateinit var adapter: GamesListAdapter
    @Mock lateinit var eventDelegate: GamesListEventDelegate

    lateinit var classToTest: GamesListView

    @Before
    fun setUp() {
        classToTest = GamesListView(adapter, activity)
        classToTest.eventDelegate = eventDelegate
    }

    @Test
    fun showsLoading() {

        classToTest.setLoading(true)

        assertEquals(true, classToTest.loading.get())

    }

    @Test
    fun hidesLoading() {

        classToTest.setLoading(false)

        assertEquals(false, classToTest.loading.get())

    }

    @Test
    fun delegatesTrimmedFilterTextChanged() {

        val text = "  text  "
        val trimmedText = "text"

        classToTest.onTextChanged(text, 0, 0, 0)

        verify(eventDelegate).filterTextChanged(trimmedText)

    }

    @Test
    fun setsGamesToAdapter() {

        val games = listOf(TestGames.testGame)

        classToTest.displayGames(games)

        verify(adapter).games = games

    }

    @Test
    fun setsHighlightTextToAdapter() {

        val text = "text"

        classToTest.highlightGamesWithText(text)

        verify(adapter).highlightText = text

    }

}