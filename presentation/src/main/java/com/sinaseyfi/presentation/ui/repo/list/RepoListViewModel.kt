package com.sinaseyfi.presentation.ui.repo.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.usecases.GetRepositoriesFlowUseCase
import com.sinaseyfi.domain.usecases.GetRepositoriesPagingFlowUseCase
import com.sinaseyfi.domain.usecases.UpdateRepositoriesUseCase
import com.sinaseyfi.presentation.ui.adapters.mappers.RepoModelMapper
import com.sinaseyfi.presentation.ui.adapters.models.RepoListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.BaseViewModel
import com.sinaseyfi.presentation.ui.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val updateRepositoriesUseCase: UpdateRepositoriesUseCase,
    private val getRepositoriesFlowUseCase: GetRepositoriesFlowUseCase,
    private val getRepositoriesPagingFlowUseCase: GetRepositoriesPagingFlowUseCase,
    private val repoModelMapper: RepoModelMapper
): BaseViewModel<RepoListState>(RepoListState()) {

    private val _updatingDb = MutableStateFlow(false)
    val updatingDb: StateFlow<Boolean> = _updatingDb

    private val _since = MutableStateFlow(0L)

    init {
        launch {
            combine(updatingDb, getRepoListFlow()) { updatingDb, listDb ->
                RepoListState(isLoading = updatingDb && listDb.isEmpty())
            }.collect {
                _viewState.value = it
            }
        }
        launch {
            getRepoListFlow().collect { list ->
                _since.value = list.maxOfOrNull { item -> item.id } ?: 0L
            }
        }
    }

    fun updateRepositoryList() {
        launch { updateRepositoriesUseCase.perform(_since.value) {
            onStart = { _updatingDb.value = true }
            onError = { logger.error(it) }
            onFinish = { _updatingDb.value = false }
        } }
    }

    fun isUpdatingDb(): Boolean = updatingDb.value

    fun loadMore() { updateRepositoryList() }

    private fun getRepoListFlow(): Flow<List<RepositoryDataModel>> =
        getRepositoriesFlowUseCase.perform(null)

    // Mapping for recyclerView
    fun getRepoListFlowMapped(): Flow<List<RepoListRecyclerItemModel>> =
        getRepoListFlow().map { list -> list.map { item -> repoModelMapper.mapToModel(item) } }

    // For paging approach, but didn't work
    private fun getRepoListPagingFlow(): Flow<PagingData<RepositoryDataModel>> =
        getRepositoriesPagingFlowUseCase.perform(null).cachedIn(viewModelScope)

    // Mapping for recyclerView
    fun getRepoListPagingFlowMapped(): Flow<PagingData<RepoListRecyclerItemModel>> =
        getRepoListPagingFlow().map { pd -> pd.map { item -> repoModelMapper.mapToModel(item) } }

}