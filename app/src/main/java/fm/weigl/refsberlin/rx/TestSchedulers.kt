package fm.weigl.refsberlin.rx

import rx.Scheduler

/**
 * Created by asco on 15.07.16.
 */
class TestSchedulers : Schedulers {

    override fun main(): Scheduler {
        return rx.schedulers.Schedulers.immediate()
    }

    override fun new(): Scheduler {
        return rx.schedulers.Schedulers.immediate()
    }
}