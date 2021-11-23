package com.sinaseyfi.presentation.ui.adapters.mappers

import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.presentation.ui.adapters.models.RepoListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.ModelMapper
import javax.inject.Inject

class RepoModelMapper @Inject constructor(): ModelMapper<RepositoryDataModel, RepoListRecyclerItemModel> {
    override fun mapToModel(dataModel: RepositoryDataModel): RepoListRecyclerItemModel =
        RepoListRecyclerItemModel(
            dataModel.id,
            dataModel.name,
            dataModel.owner.login,
            dataModel.private,
            dataModel.description ?: ""
        )
}