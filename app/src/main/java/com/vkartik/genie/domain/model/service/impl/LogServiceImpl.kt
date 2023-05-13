package com.vkartik.genie.domain.model.service.impl

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.vkartik.genie.domain.model.service.LogService
import javax.inject.Inject

class LogServiceImpl @Inject constructor(): LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }
}