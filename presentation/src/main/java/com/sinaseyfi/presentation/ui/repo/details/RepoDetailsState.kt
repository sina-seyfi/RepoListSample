package com.sinaseyfi.presentation.ui.repo.details

import com.sinaseyfi.presentation.ui.base.ViewState

data class RepoDetailsState(
    override val isLoading: Boolean = false,
    val collaboratorsCanNotBeLoaded: Boolean = false
): ViewState