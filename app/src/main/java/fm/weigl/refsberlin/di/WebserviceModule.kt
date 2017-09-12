package fm.weigl.refsberlin.di

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.gameslist.net.GamesService
import fm.weigl.refsberlin.net.WebConfig
import fm.weigl.refsberlin.update.net.AppVersionService
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
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
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient())
            .baseUrl(WebConfig.GAMES_URL)
            .build()

    private fun okHttpClient(): OkHttpClient {

        val interceptor = Interceptor { chain ->
            val original = chain!!.request();
            val request = original.newBuilder().build()
            chain.proceed(request)
        }

        val client = OkHttpClient()
        client.interceptors().add(interceptor)
        return client

    }

}