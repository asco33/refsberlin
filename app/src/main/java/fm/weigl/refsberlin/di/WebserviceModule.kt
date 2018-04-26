package fm.weigl.refsberlin.di

import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.gameslist.net.GamesService
import fm.weigl.refsberlin.net.WebConfig
import fm.weigl.refsberlin.update.net.AppVersionService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class WebserviceModule {

    @Provides
    @Singleton
    fun gamesService(): GamesService = gamesRetrofit().create(GamesService::class.java)

    @Provides
    @Singleton
    fun appVersionService(): AppVersionService = gamesRetrofit().create(AppVersionService::class.java)

    private fun gamesRetrofit() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .baseUrl(WebConfig.GAMES_URL)
            .build()
}