package com.sinaseyfi.presentation.ui.adapters.mappers

import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.presentation.ui.adapters.models.CollaboratorListRecyclerItemModel
import com.sinaseyfi.presentation.ui.base.ModelMapper
import javax.inject.Inject

class CollaboratorModelMapper @Inject constructor(): ModelMapper<RepositoryCollaboratorDataModel, CollaboratorListRecyclerItemModel> {
    override fun mapToModel(dataModel: RepositoryCollaboratorDataModel): CollaboratorListRecyclerItemModel =
        CollaboratorListRecyclerItemModel(
            dataModel.id,
            dataModel.avatarUrl,
            dataModel.login,
            dataModel.htmlUrl
        )
}