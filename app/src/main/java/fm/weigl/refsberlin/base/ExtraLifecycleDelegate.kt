package fm.weigl.refsberlin.base

interface ExtraLifecycleDelegate {
    fun onBackPressed(): Boolean = false // return if backPressed is handled by delegate
}