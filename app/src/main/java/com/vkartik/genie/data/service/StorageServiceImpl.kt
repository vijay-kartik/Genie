package com.vkartik.genie.data.service

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.domain.service.AccountService
import com.vkartik.genie.domain.service.StorageService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val firestore: FirebaseFirestore, private val accountService: AccountService): StorageService {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val accounts: Flow<List<Account>>
        get() = accountService.currentUser.flatMapLatest {
            user -> currentCollection(user.id).snapshots().map { snapShot -> snapShot.toObjects() }
        }

    override suspend fun getAccount(accountId: String): Account? {
        return currentCollection(accountService.currentUserId).document(accountId).get().await().toObject()
    }

    override suspend fun save(account: Account): String {
        return currentCollection(accountService.currentUserId).add(account).await().id
    }

    override suspend fun update(account: Account) {
        currentCollection(accountService.currentUserId).document(account.name).set(account).await()
    }

    override suspend fun delete(accountId: String) {
        currentCollection(accountService.currentUserId).document(accountId).delete().await()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(ACCOUNT_COLLECTION)


    companion object {
        private const val USER_COLLECTION = "users"
        private const val ACCOUNT_COLLECTION = "accounts"
    }
}