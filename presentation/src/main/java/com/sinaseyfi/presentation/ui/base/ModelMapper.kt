package com.sinaseyfi.presentation.ui.base

import com.sinaseyfi.domain.base.DataModel

interface ModelMapper<D: DataModel, R: RecyclerItemModel> {

    fun mapToModel(dataModel: D): R

}