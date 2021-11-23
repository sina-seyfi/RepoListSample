package com.sinaseyfi.presentation.ui.repo.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.domain.models.request.RepositoryCollaboratorsRequestDataModel
import com.sinaseyfi.domain.models.request.RepositoryDetailsRequestDataModel
import com.sinaseyfi.domain.usecases.GetCollaboratorsPagingFlowUseCase
import com.sinaseyfi.domain.usecases.GetRepositoryDetailsUseCase
import com.sinaseyfi.presentation.ui.adapters.mappers.CollaboratorModelMapper
import com.sinaseyfi.presentation.ui.adapters.models.CollaboratorListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BaseViewModel
import com.sinaseyfi.presentation.ui.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase,
    private val getCollaboratorsPagingFlowUseCase: GetCollaboratorsPagingFlowUseCase,
    private val collaboratorModelMapper: CollaboratorModelMapper
): BaseViewModel<RepoDetailsState>(RepoDetailsState()) {

    private val _repoDetails = MutableLiveData<RepositoryDetailsDataModel>()
    val repoDetails: LiveData<RepositoryDetailsDataModel> = _repoDetails

    fun fetchDetails(owner: String, repo: String) {
        launch {
            getRepositoryDetailsUseCase.perform(
                RepositoryDetailsRequestDataModel(owner, repo)
            ) {
                onSuccess = { result -> _repoDetails.value = result.data!! } // I don't know why (!!)
                onError = {
                    _viewState.value = _viewState.value.copy(
                        collaboratorsCanNotBeLoaded = true
                    )
                }
            }
        }
    }

    fun fetchCollaborators(owner: String, repo: String): Flow<PagingData<RepositoryCollaboratorDataModel>> =
        getCollaboratorsPagingFlowUseCase.perform(
            RepositoryCollaboratorsRequestDataModel(owner, repo)
        ).cachedIn(viewModelScope)

    fun fetchCollaboratorsMapped(owner: String, repo: String): Flow<PagingData<CollaboratorListRecyclerItemModel>> =
        fetchCollaborators(owner, repo).map { pd -> pd.map { dataModel ->
            collaboratorModelMapper.mapToModel(dataModel)
        } }

}