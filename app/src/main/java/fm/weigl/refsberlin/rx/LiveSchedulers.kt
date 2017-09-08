package fm.weigl.refsberlin.rx

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class LiveSchedulers @Inject constructor() : fm.weigl.refsberlin.rx.Schedulers {

    override fun main() = AndroidSchedulers.mainThread()
    override fun new() = Schedulers.newThread()

}