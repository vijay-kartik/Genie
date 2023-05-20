package com.vkartik.genie.domain.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}