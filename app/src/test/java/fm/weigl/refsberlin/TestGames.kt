package fm.weigl.refsberlin

import fm.weigl.refdata.Game
import fm.weigl.refdata.GamePlace
import fm.weigl.refdata.GameReferee
import fm.weigl.refdata.Team
import java.util.*

class TestGames {

    companion object {

        val startTime = 999L
        val team1Name = "team1Name"
        val team1 = Team(team1Name)
        val team2Name = "team2Name"
        val team2 = Team(team2Name)
        val ref1Name = "ref1"
        val ref1Pos = "BJ"
        val ref1 = GameReferee(ref1Name, ref1Pos)
        val ref2Name = "ref2"
        val ref2Pos = "U"
        val ref2 = GameReferee(ref2Name, ref2Pos)
        val placeName = "placeName"
        val place = GamePlace(placeName)

        val testGame = Game(listOf(ref1, ref2), Date(startTime), place, team1, team2)

    }

}