package com.vkartik.genie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkartik.genie.domain.model.service.LogService
import com.vkartik.genie.ui.snackbar.SnackbarManager
import com.vkartik.genie.ui.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class GenieViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}