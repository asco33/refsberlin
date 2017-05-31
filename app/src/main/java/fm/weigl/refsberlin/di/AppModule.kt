package fm.weigl.refsberlin.di

import android.content.Context
import android.content.res.Resources
import com.crashlytics.android.answers.Answers
import dagger.Module
import dagger.Provides
import fm.weigl.refsberlin.rx.LiveSchedulers
import fm.weigl.refsberlin.rx.Schedulers

/**
 * Created by asco on 15.07.16.
 */

@Module
class AppModule(val context: Context) {


    @Provides
    fun schedulers(): Schedulers {
        return LiveSchedulers()
    }

    @Provides
    fun resources(): Resources {
        return context.resources
    }

    @Provides
    fun context(): Context {
        return context.applicationContext
    }

    @Provides
    fun answers(): Answers {
        return Answers.getInstance()
    }

}