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
import fm.weigl.refsberlin.base.LoadingState
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class GamesListViewTest {

    val activity: Activity = mock()
    val toaster: Toaster = mock()
    val resources: Resources = mock()
    val adapter: GamesListAdapter = mock()
    val delegate: GamesListEventDelegate = mock()
    val editText: EditText = mock()

    val classToTest = GamesListView(adapter, activity, toaster, resources)

    @Before
    fun setUp() {
        classToTest.delegate = delegate
        classToTest.setViews(mock(), editText)
    }

    @Test
    fun setsLoadingStatesCorrectly() {

        classToTest.setLoadingState(LoadingState.DONE)
        assertEquals(LoadingState.DONE, classToTest.loadingState.get())

        classToTest.setLoadingState(LoadingState.LOADING)
        assertEquals(LoadingState.LOADING, classToTest.loadingState.get())

        classToTest.setLoadingState(LoadingState.REFRESHING)
        assertEquals(LoadingState.REFRESHING, classToTest.loadingState.get())

    }

    @Test
    fun delegatesPullToRefresh() {

        classToTest.onRefresh()

        then(delegate).should().refreshPulled()

    }

    @Test
    fun delegatesFilterTextChanged() {

        classToTest.onTextChanged()

        verify(delegate).filterTextChanged()

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