package fm.weigl.refsberlin.calendar

import android.content.res.Resources
import android.text.format.DateUtils
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.UINavigator
import javax.inject.Inject

class CalendarEventCreator @Inject constructor(
        private val uiNavigator: UINavigator,
        private val resources: Resources
) {
    companion object {
        val THREE_HOURS = DateUtils.HOUR_IN_MILLIS * 3
    }


    fun createEventForGame(game: Game) {
        val startTime = game.date.time
        val endTime = game.date.time + THREE_HOURS
        val eventName = resources.getString(R.string.match_representation, game.homeTeam.name, game.awayTeam.name)
        var crew = resources.getString(R.string.crew)
        game.referees.forEach {
            crew = crew
                    .plus("\n")
                    .plus(resources.getString(R.string.referee_representation,
                            it.name,
                            it.position.toString()))
        }

        val location = game.place.place

        uiNavigator.createCalendarEventForGame(startTime, endTime, eventName, crew, location)

    }

}