package fm.weigl.refsberlin.di

import android.content.Context
import android.content.res.Resources
import com.crashlytics.android.answers.Answers
import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.rx.LiveSchedulers
import fm.weigl.refsberlin.rx.Schedulers

@Module
class AppModule(val context: Context) {


    @Provides
    fun schedulers(): Schedulers = LiveSchedulers()

    @Provides
    fun resources(): Resources = context.resources

    @Provides
    fun context(): Context = context.applicationContext

    @Provides
    fun answers(): Answers = Answers.getInstance()

}