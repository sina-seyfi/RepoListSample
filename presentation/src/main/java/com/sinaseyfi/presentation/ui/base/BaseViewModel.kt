package com.sinaseyfi.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.sinaseyfi.log.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


abstract class BaseViewModel<V : ViewState>(
    initViewState: V
) : ViewModel()
{

    @Inject lateinit var logger: Logger
    
    protected val _viewState = MutableStateFlow(initViewState)
    val viewState: StateFlow<V> = _viewState

}