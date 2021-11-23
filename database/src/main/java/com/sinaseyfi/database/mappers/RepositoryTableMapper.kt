package com.sinaseyfi.database.mappers

import com.sinaseyfi.database.TableMapper
import com.sinaseyfi.database.entities.RepositoryTable
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.models.RepositoryOwnerDataModel
import javax.inject.Inject

class RepositoryTableMapper @Inject constructor(): TableMapper<RepositoryTable, RepositoryDataModel> {

    override fun mapToData(table: RepositoryTable): RepositoryDataModel =
        RepositoryDataModel(
            table.id,
            table.repoName,
            RepositoryOwnerDataModel(0L, "", "", ""),
            table.privat3,
            table.htmlUrl,
            table.description
        )

    override fun mapToTable(data: RepositoryDataModel): RepositoryTable =
        RepositoryTable(
            data.id,
            data.name,
            data.private,
            data.htmlUrl,
            data.description ?: ""
        )

}