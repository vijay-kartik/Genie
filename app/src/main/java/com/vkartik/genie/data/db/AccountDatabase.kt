package com.vkartik.genie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vkartik.genie.data.AccountEntity
import com.vkartik.genie.data.AccountDAO

@Database(entities = [AccountEntity::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDAO

    companion object {
        @Volatile
        private var Instance: AccountDatabase? = null

        fun getDatabase(context: Context): AccountDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AccountDatabase::class.java, "account_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}