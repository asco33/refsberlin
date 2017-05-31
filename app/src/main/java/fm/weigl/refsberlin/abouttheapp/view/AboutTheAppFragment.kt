package fm.weigl.refsberlin.abouttheapp.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.BaseFragment
import fm.weigl.refsberlin.databinding.AboutTheAppFragmentBinding
import javax.inject.Inject

class AboutTheAppFragment : BaseFragment() {

    @Inject lateinit var viewModel: AboutTheAppViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        activityComponent.inject(this)

        val binding = DataBindingUtil.inflate<AboutTheAppFragmentBinding>(inflater,
                                                                          R.layout.about_the_app_fragment,
                                                                          container,
                                                                          false)

        binding.model = viewModel

        return binding.root
    }

}