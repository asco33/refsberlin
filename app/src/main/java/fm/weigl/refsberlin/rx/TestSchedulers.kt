package fm.weigl.refsberlin.rx

class TestSchedulers : Schedulers {

    override fun main() = rx.schedulers.Schedulers.immediate()

    override fun new() = rx.schedulers.Schedulers.immediate()
}