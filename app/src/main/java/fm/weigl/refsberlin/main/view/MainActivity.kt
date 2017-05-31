package fm.weigl.refsberlin.main.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.BaseActivity
import fm.weigl.refsberlin.databinding.MainActivityBinding
import fm.weigl.refsberlin.main.presenter.MainNavigator
import fm.weigl.refsberlin.main.presenter.MainPresenter
import javax.inject.Inject

/**
 * Created by asco on 4/27/17.
 */
class MainActivity : BaseActivity() {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var navigationDrawerView: NavigationDrawerView
    @Inject lateinit var mainNavigator: MainNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        val toolbar = binding.mainInclude.toolbar
        setSupportActionBar(toolbar)

        navigationDrawerView.setViews(binding.drawerLayout, binding.navView, toolbar)
        navigationDrawerView.delegate = presenter

        setExtraLifecycleDelegates(mainNavigator)

        presenter.start()
    }

}

