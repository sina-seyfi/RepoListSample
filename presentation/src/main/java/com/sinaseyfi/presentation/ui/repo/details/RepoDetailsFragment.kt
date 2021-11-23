package com.sinaseyfi.presentation.ui.repo.details

import android.content.res.ColorStateList
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.presentation.R
import com.sinaseyfi.presentation.databinding.FragmentRepoDetailsBinding
import com.sinaseyfi.presentation.ui.adapters.CollaboratorPagingAdapter
import com.sinaseyfi.presentation.ui.adapters.decorations.VerticalMarginItemDecoration
import com.sinaseyfi.presentation.ui.base.BaseFragment
import com.sinaseyfi.presentation.ui.factories.RepositoryPrivacyBackgroundTintColor
import com.sinaseyfi.presentation.ui.factories.RepositoryPrivacyIconFactory
import com.sinaseyfi.presentation.ui.factories.RepositoryPrivacyTintColor
import com.sinaseyfi.presentation.ui.utils.loadFromUrl
import com.sinaseyfi.presentation.ui.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RepoDetailsFragment: BaseFragment<RepoDetailsState, RepoDetailsViewModel, FragmentRepoDetailsBinding>() {

    override val viewModel: RepoDetailsViewModel by viewModels()

    override fun renderView(state: RepoDetailsState) {
    }

    private lateinit var collaboratorPagingAdapter: CollaboratorPagingAdapter

    private val args: RepoDetailsFragmentArgs by navArgs()

    override fun init() {
        super.init()
        collaboratorPagingAdapter = CollaboratorPagingAdapter()
        viewBinding.apply {
            collaboratorsListRv.adapter = collaboratorPagingAdapter
            collaboratorsListRv.addItemDecoration(VerticalMarginItemDecoration(requireContext()))
        }
        viewModel.apply {
            repoDetails.observe(viewLifecycleOwner) { model -> showDetails(model) }
            fetchDetails(args.owner, args.repo)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.fetchCollaboratorsMapped(args.owner, args.repo).collect {
                collaboratorPagingAdapter.submitData(it)
            }
        }
    }

    private fun showDetails(model: RepositoryDetailsDataModel) {
        viewBinding.apply {
            repoNameTv.text = model.name
            ownerNameTv.text = model.owner.login
            starCountTv.text = model.stargazersCount.toString()
            forkCountTv.text = resources.getQuantityString(R.plurals.fork_number, model.forks, model.forks)
            repoHttpUrlTv.text = model.htmlUrl
            repoDescriptionTv.text = model.description
            avatarSiv.loadFromUrl(model.owner.avatarUrl)
            privateSiv.apply {
                setImageResource(RepositoryPrivacyIconFactory.create(model.private))
                imageTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources,
                        RepositoryPrivacyTintColor.create(model.private),
                        null
                    )
                )
                backgroundTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources,
                        RepositoryPrivacyBackgroundTintColor.create(model.private),
                        null
                    )
                )
            }
        }
    }

    override fun createViewBinding(inflater: LayoutInflater): FragmentRepoDetailsBinding =
        FragmentRepoDetailsBinding.inflate(inflater)

}