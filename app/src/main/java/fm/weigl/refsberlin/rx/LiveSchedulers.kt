package fm.weigl.refsberlin.rx

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by asco on 15.07.16.
 */
class LiveSchedulers @Inject constructor() : fm.weigl.refsberlin.rx.Schedulers {

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun new(): Scheduler {
        return Schedulers.newThread()
    }

}