package fm.weigl.refsberlin.di

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.gameslist.net.GamesService
import fm.weigl.refsberlin.net.WebConfig
import fm.weigl.refsberlin.security.ApiKey
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import javax.inject.Singleton

/**
 * Created by asco on 1/31/17.
 */

@Module
class WebserviceModule() {

    companion object {
        const val HEADER_KEY_AUTH = "Authorization"
    }

    @Provides
    @Singleton
    fun gamesService(): GamesService {
        val retroFit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .baseUrl(WebConfig.GAMES_URL)
                .build()
        return retroFit.create(GamesService::class.java)
    }

    private fun okHttpClient(): OkHttpClient {

        val interceptor = Interceptor { chain ->
            val original = chain!!.request();

            val requestBuilder = original.newBuilder()
                    .header(HEADER_KEY_AUTH, ApiKey.API_KEY)

            val request = requestBuilder.build();
            chain.proceed(request)
        }

        val client = OkHttpClient()
        client.interceptors().add(interceptor)
        return client

    }

}