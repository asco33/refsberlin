package fm.weigl.refsberlin.rx

import rx.Scheduler

/**
 * Created by asco on 15.07.16.
 */
interface Schedulers {
    fun main(): Scheduler
    fun new(): Scheduler
}