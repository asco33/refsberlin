package fm.weigl.refsberlin.privacy

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.BaseFragment
import fm.weigl.refsberlin.databinding.PrivacyFragmentBinding

class PrivacyFragment : BaseFragment() {

    companion object {
        private const val PRIVACY_URL = "http://fmweigl.atwebpages.com/datenschutz.html"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<PrivacyFragmentBinding>(inflater, R.layout.privacy_fragment, container, false)
        binding.webView.loadUrl(PRIVACY_URL)
        return binding.root
    }
}