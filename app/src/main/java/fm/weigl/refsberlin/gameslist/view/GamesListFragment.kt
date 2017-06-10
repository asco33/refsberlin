package fm.weigl.refsberlin.gameslist.view


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.base.BaseFragment
import fm.weigl.refsberlin.databinding.GamesListFragmentBinding
import fm.weigl.refsberlin.error.view.ErrorScreen
import fm.weigl.refsberlin.gameslist.presenter.GamesListPresenter
import javax.inject.Inject

class GamesListFragment : BaseFragment() {

    @Inject lateinit var presenter: GamesListPresenter
    @Inject lateinit var gamesListView: GamesListView
    @Inject lateinit var errorScreen: ErrorScreen
    private lateinit var binding: GamesListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.games_list_fragment, container, false)

        activityComponent.inject(this)

        binding.viewModel = gamesListView
        binding.errorScreen.error = errorScreen

        presenter.errorScreen = errorScreen
        errorScreen.delegate = presenter
        gamesListView.setViews(binding.rvGameslist, binding.etGameslistFilter)
        presenter.view = gamesListView
        gamesListView.eventDelegate = presenter

        presenter.loadGames()

        return binding.root
    }

}
