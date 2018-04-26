package fm.weigl.refsberlin.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.crashlytics.android.answers.Answers
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.prefs.PrefsProvider
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    fun resources(): Resources = context.resources

    @Provides
    fun context(): Context = context.applicationContext

    @Provides
    fun answers(): Answers = Answers.getInstance()

    @Provides
    fun prefs(): SharedPreferences = PrefsProvider.prefs(context)

    @Provides
    @Singleton
    fun gson(): Gson = Gson()

}