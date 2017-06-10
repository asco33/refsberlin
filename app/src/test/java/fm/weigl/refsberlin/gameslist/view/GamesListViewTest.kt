package fm.weigl.refsberlin.gameslist.view

import android.app.Activity
import android.content.res.Resources
import android.text.Editable
import android.widget.EditText
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.TestGames
import fm.weigl.refsberlin.android.Toaster
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
    @Mock lateinit var toaster: Toaster
    @Mock lateinit var resources: Resources
    @Mock lateinit var adapter: GamesListAdapter
    @Mock lateinit var eventDelegate: GamesListEventDelegate
    @Mock lateinit var editText: EditText

    lateinit var classToTest: GamesListView

    @Before
    fun setUp() {
        classToTest = GamesListView(adapter, activity, toaster, resources)
        classToTest.eventDelegate = eventDelegate
        classToTest.setViews(mock(), editText)
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
    fun delegatesFilterTextChanged() {

        val text = "  text  "

        classToTest.onTextChanged(text, 0, 0, 0)

        verify(eventDelegate).filterTextChanged()

    }

    @Test
    fun returnsTrimmedFilterText() {

        val text = " text "
        val editable = mock<Editable>()
        given(editable.toString()).willReturn(text)

        given(editText.text).willReturn(editable)

        assertEquals("text", classToTest.getFilterText())

    }

    @Test
    fun setsGamesToAdapter() {

        val games = listOf(TestGames.testGame)

        given(resources.getString(any(), any())).willReturn("")

        classToTest.displayGames(games)

        verify(adapter).games = games

    }

    @Test
    fun setsHighlightTextToAdapter() {

        val text = "text"

        classToTest.highlightGamesWithText(text)

        verify(adapter).highlightText = text

    }

    @Test
    fun displaysNumberOfGames() {

        val text = "text"
        val games = listOf(TestGames.testGame, TestGames.testGame)
        given(resources.getString(R.string.number_of_games, 2)).willReturn(text)

        classToTest.displayGames(games)

        then(toaster).should().showToast(text)

    }

    @Test
    fun clearsEditTextWhenClearButtonClicked() {

        classToTest.clearButtonClicked()

        then(editText).should().setText("")

    }

}