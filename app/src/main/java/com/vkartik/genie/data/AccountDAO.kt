package com.vkartik.genie.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: AccountEntity)

    @Update
    suspend fun update(account: AccountEntity)

    @Delete
    suspend fun delete(account: AccountEntity)

    @Query("SELECT * from accounts where id = :id")
    fun getAccount(id: Int): Flow<AccountEntity>

    @Query("SELECT * from accounts")
    fun getAllAccounts(): Flow<List<AccountEntity>>
}