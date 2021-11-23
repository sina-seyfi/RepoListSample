package com.sinaseyfi.database.mappers

import com.sinaseyfi.database.TableMapper
import com.sinaseyfi.database.entities.RepositoryAndOwner
import com.sinaseyfi.domain.models.RepositoryDataModel
import javax.inject.Inject

class RepositoryAndOwnerTableMapper @Inject constructor(
    private val repositoryTableMapper: RepositoryTableMapper,
    private val ownerTableMapper: OwnerTableMapper
): TableMapper<RepositoryAndOwner, RepositoryDataModel> {
    override fun mapToData(table: RepositoryAndOwner): RepositoryDataModel =
        repositoryTableMapper.mapToData(table.repository).copy(
            owner = ownerTableMapper.mapToData(table.owner)
        )

    override fun mapToTable(data: RepositoryDataModel): RepositoryAndOwner =
        RepositoryAndOwner(
            repositoryTableMapper.mapToTable(data),
            ownerTableMapper.mapToTable(data.owner)
        )
}