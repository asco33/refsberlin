package fm.weigl.refsberlin.gameslist.view

import android.text.Spannable
import android.view.View
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.TestGames
import fm.weigl.refsberlin.TestGames.Companion.ref1
import fm.weigl.refsberlin.TestGames.Companion.ref2
import fm.weigl.refsberlin.android.ContextCompatWrapper
import fm.weigl.refsberlin.gameslist.presenter.GameInfoFormatter
import fm.weigl.refsberlin.view.TextHighlighter
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by asco on 15.07.16.
 */

@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {

    @Mock lateinit var highlighter: TextHighlighter
    @Mock lateinit var contextCompat: ContextCompatWrapper
    @Mock lateinit var formatter: GameInfoFormatter
    @Mock lateinit var ref0Spannable: Spannable
    @Mock lateinit var ref1Spannable: Spannable
    @Mock lateinit var teamsSpannable: Spannable
    @Mock lateinit var eventIconCallback: () -> Unit
    @Mock lateinit var navigationIconCallback: () -> Unit

    val game = TestGames.testGame
    val textToHighlight = "textToHighlight"

    lateinit var classToTest: GameViewModel

    @Before
    fun setUp() {
        classToTest = GameViewModel(contextCompat, highlighter, formatter)

        val teamsFormatted = "teamsFormatted"
        given(formatter.teams(game)).willReturn(teamsFormatted)
        given(highlighter.highlightText(teamsFormatted)).willReturn(teamsSpannable)

        val ref0Formatted = "ref0Formatted"
        val ref1Formatted = "ref1Formatted"
        given(formatter.refereeWithPosition(ref1)).willReturn(ref0Formatted)
        given(highlighter.highlightText(ref0Formatted)).willReturn(ref0Spannable)
        given(formatter.refereeWithPosition(ref2)).willReturn(ref1Formatted)
        given(highlighter.highlightText(ref1Formatted)).willReturn(ref1Spannable)
    }

    @Test
    fun setsHighlighterColor() {
        val color = 55
        given(contextCompat.getColor(R.color.colorAccent)).willReturn(color)
        classToTest = GameViewModel(contextCompat, highlighter, formatter)

        classToTest.setGameAndTextToHighlight(game, "")

        verify(highlighter).color = color
    }

    @Test
    fun setsHighlighterText() {
        val text = "text"

        classToTest.setGameAndTextToHighlight(game, text)

        verify(highlighter).toHighlight = text
    }

    @Test
    fun setsTeams() {

        classToTest.setGameAndTextToHighlight(game, textToHighlight)

        assertEquals(teamsSpannable, classToTest.teams.get())

    }


    @Test
    fun setsReferee() {

        classToTest.setGameAndTextToHighlight(game, textToHighlight)

        assertEquals(ref0Spannable, classToTest.ref0.get())

    }

    @Test
    fun setsRefereeToNullIfNotExists() {

        classToTest.setGameAndTextToHighlight(game, textToHighlight)

        assertNull(classToTest.ref7.get())

    }

    @Test
    fun delegatesEventIconClick() {

        classToTest.onEventIconClick = eventIconCallback

        classToTest.eventIconClicked(mock(View::class.java))

        verify(eventIconCallback).invoke()

    }

    @Test
    fun delegatesNavigationIconClick() {

        classToTest.onNavigationIconClick = navigationIconCallback

        classToTest.navigationIconClicked(mock(View::class.java))

        verify(navigationIconCallback).invoke()

    }


}