package fm.weigl.refsberlin.rx

import rx.Scheduler

interface Schedulers {
    fun main(): Scheduler
    fun new(): Scheduler
}