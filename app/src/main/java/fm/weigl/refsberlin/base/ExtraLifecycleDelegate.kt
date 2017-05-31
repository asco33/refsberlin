package fm.weigl.refsberlin.base

/**
 * Created by asco on 5/23/17.
 */
interface ExtraLifecycleDelegate {
    fun onBackPressed(): Boolean // return if backPressed is handled by delegate
}