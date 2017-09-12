package fm.weigl.refsberlin.main.view

import android.databinding.DataBindingUtil
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.BaseActivity
import fm.weigl.refsberlin.databinding.MainActivityBinding
import fm.weigl.refsberlin.di.ActivityComponent
import fm.weigl.refsberlin.main.presenter.MainNavigator
import fm.weigl.refsberlin.main.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var navigationDrawerView: NavigationDrawerView
    @Inject lateinit var mainNavigator: MainNavigator

    override fun componentReady(component: ActivityComponent) {
        component.inject(this)

        setMainLifecycleDelegates(presenter)
        setExtraLifecycleDelegates(navigationDrawerView)

        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        val toolbar = binding.mainInclude.toolbar
        setSupportActionBar(toolbar)

        navigationDrawerView.setViews(binding.drawerLayout, binding.navView, toolbar)
        navigationDrawerView.delegate = presenter

        mainNavigator.delegate = navigationDrawerView
    }

}

