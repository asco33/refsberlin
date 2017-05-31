package fm.weigl.refsberlin.di

import android.content.res.Resources
import dagger.Component
import fm.weigl.refsberlin.base.App
import fm.weigl.refsberlin.gameslist.net.GamesRepository
import fm.weigl.refsberlin.rx.Schedulers
import javax.inject.Singleton

/**
 * Created by asco on 15.07.16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, WebserviceModule::class))
interface AppComponent {
    fun injectTo(app: App)
    fun gamesRepository(): GamesRepository
    fun schedulers(): Schedulers
    fun resources(): Resources
}