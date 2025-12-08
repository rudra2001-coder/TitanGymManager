package com.rudra.titangymmanager.data

import android.content.Context
import androidx.room.*
import com.rudra.titangymmanager.data.dao.ExpenseDao
import com.rudra.titangymmanager.data.dao.MemberDao
import com.rudra.titangymmanager.data.dao.MembershipPackageDao
import com.rudra.titangymmanager.data.dao.PaymentDao
import com.rudra.titangymmanager.data.model.Expense
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.model.MembershipPackage
import com.rudra.titangymmanager.data.model.Payment

@Database(entities = [Member::class, MembershipPackage::class, Payment::class, Expense::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun membershipPackageDao(): MembershipPackageDao
    abstract fun paymentDao(): PaymentDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "titan_gym_manager_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
