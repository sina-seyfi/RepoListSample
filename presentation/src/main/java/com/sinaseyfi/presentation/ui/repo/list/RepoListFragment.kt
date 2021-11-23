package com.sinaseyfi.presentation.ui.repo.list

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sinaseyfi.presentation.R
import com.sinaseyfi.presentation.databinding.FragmentRepoListBinding
import com.sinaseyfi.presentation.ui.adapters.RepoListAdapter
import com.sinaseyfi.presentation.ui.adapters.RepoPagingAdapter
import com.sinaseyfi.presentation.ui.adapters.decorations.VerticalMarginItemDecoration
import com.sinaseyfi.presentation.ui.adapters.models.RepoListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BaseFragment
import com.sinaseyfi.presentation.ui.base.OnRecyclerItemClickListener
import com.sinaseyfi.presentation.ui.utils.setInvisibility
import com.sinaseyfi.presentation.ui.utils.setVisibility
import com.sinaseyfi.presentation.ui.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoListFragment: BaseFragment<RepoListState, RepoListViewModel, FragmentRepoListBinding>() {

    override val viewModel: RepoListViewModel by viewModels()

    private lateinit var repoListAdapter: RepoListAdapter
    // First tried to implement it using Android Paging.
//    private lateinit var repoPagingAdapter: RepoPagingAdapter

    override fun init() {
        super.init()
        repoListAdapter = RepoListAdapter(onRepoListItemClickListener)
//        repoPagingAdapter = RepoPagingAdapter(onRepoListItemClickListener)
        viewBinding.apply {
            repoListRv.adapter = repoListAdapter
            repoListRv.addItemDecoration(VerticalMarginItemDecoration(requireContext()))
            repoListRv.addOnScrollListener(rvEndlessScrollListener)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getRepoListFlowMapped().collect {
                repoListAdapter.submitList(it)
            }
        }
        viewModel.updateRepositoryList()
    }

    private val rvEndlessScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) { //check for scroll down
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val pastVisibleItems = (recyclerView.layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()
                if (!viewModel.isUpdatingDb()) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        viewModel.loadMore()
                    }
                }
            }
        }
    }

    private val onRepoListItemClickListener = object: OnRecyclerItemClickListener<RepoListRecyclerItemModel> {
        override fun onItemClicked(item: RepoListRecyclerItemModel, viewId: Long, position: Int?) {
            if(item.ownerName.isEmpty()) { showSnackbar(R.string.details_error) }
            else {
                navController.navigate(
                    RepoListFragmentDirections.actionRepoListFragmentToRepoDetailsFragment(item.ownerName, item.repoName)
                )
            }
        }
    }

    override fun deallocate() {
        super.deallocate()
        viewBinding.repoListRv.adapter = null // To solve memory leak issue
    }

    override fun renderView(state: RepoListState) {
        viewBinding.loadingPb.setVisibility(state.isLoading)
        viewBinding.repoListRv.setInvisibility(state.isLoading)
    }

    override fun createViewBinding(inflater: LayoutInflater): FragmentRepoListBinding =
        FragmentRepoListBinding.inflate(inflater)

}