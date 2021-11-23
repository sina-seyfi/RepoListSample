package com.sinaseyfi.database.mappers

import com.sinaseyfi.database.TableMapper
import com.sinaseyfi.database.entities.OwnerTable
import com.sinaseyfi.domain.models.RepositoryOwnerDataModel
import javax.inject.Inject

class OwnerTableMapper @Inject constructor(): TableMapper<OwnerTable?, RepositoryOwnerDataModel> {

    override fun mapToData(table: OwnerTable?): RepositoryOwnerDataModel =
        RepositoryOwnerDataModel(
            table?.ownerId ?: 0L,
            table?.avatarUrl ?: "",
            table?.login ?: "",
            table?.htmlUrl ?: ""
        )

    override fun mapToTable(data: RepositoryOwnerDataModel): OwnerTable =
        OwnerTable(
            id = 0L,
            ownerId = data.id,
            repoId = 0L,
            avatarUrl = data.avatarUrl,
            login = data.login,
            htmlUrl = data.htmlUrl
        )

}