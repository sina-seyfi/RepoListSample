package com.sinaseyfi.presentation.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun ViewModel.launch(block: suspend () -> Unit): Job =
    viewModelScope.launch { block.invoke() }

fun <T> ViewModel.async(block: suspend () -> T): Deferred<T> =
    viewModelScope.async { block.invoke() }