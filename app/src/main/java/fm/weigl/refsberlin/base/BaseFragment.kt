package fm.weigl.refsberlin.base

import android.support.v4.app.Fragment
import fm.weigl.refsberlin.di.ActivityComponent

open class BaseFragment : Fragment() {

    protected val activityComponent: ActivityComponent
        get() = (activity as BaseActivity).activityComponent

}