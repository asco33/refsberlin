package fm.weigl.refsberlin.di

import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Component
import fm.weigl.refsberlin.base.App
import fm.weigl.refsberlin.gameslist.net.GamesRepository
import fm.weigl.refsberlin.update.net.AppVersionRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, WebserviceModule::class])
interface AppComponent {
    fun injectTo(app: App)
    fun gamesRepository(): GamesRepository
    fun appVersionRepository(): AppVersionRepository
    fun resources(): Resources
    fun prefs(): SharedPreferences
}