package com.vkartik.genie.data.service

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.vkartik.genie.domain.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor(): LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }
}