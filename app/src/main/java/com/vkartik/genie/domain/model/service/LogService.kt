package com.vkartik.genie.domain.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}