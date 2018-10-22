package fm.weigl.refsberlin.settings.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.BaseFragment
import fm.weigl.refsberlin.databinding.SettingsFragmentBinding
import fm.weigl.refsberlin.settings.presenter.SettingsPresenter
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

    @Inject
    lateinit var view: SettingsView
    @Inject
    lateinit var presenter: SettingsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activityComponent.inject(this)
        val binding = DataBindingUtil.inflate<SettingsFragmentBinding>(layoutInflater, R.layout.settings_fragment, container, false)
        binding.viewModel = view
        presenter.view = view
        view.delegate = presenter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
    }
}
