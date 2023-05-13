package com.vkartik.genie.domain.model.service.impl

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.vkartik.genie.domain.model.User
import com.vkartik.genie.domain.model.service.AccountService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth): AccountService {
    override val currentUserId: String
        get() = TODO("Not yet implemented")
    override val hasUser: Boolean
        get() = TODO("Not yet implemented")
    override val currentUser: Flow<User>
        get() = TODO("Not yet implemented")

    override suspend fun authenticate(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun sendRecoveryEmail(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createAnonymousAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun linkAccount(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        auth.currentUser!!.linkWithCredential(credential).await()
    }

    override suspend fun deleteAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}