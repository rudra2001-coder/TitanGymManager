package com.rudra.titangymmanager.data

import android.content.Context
import androidx.room.*
import com.rudra.titangymmanager.data.dao.*
import com.rudra.titangymmanager.data.model.*

@Database(entities = [Member::class, MembershipPackage::class, Payment::class, Expense::class, Equipment::class, Trainer::class], version = 6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun membershipPackageDao(): MembershipPackageDao
    abstract fun paymentDao(): PaymentDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun trainerDao(): TrainerDao

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
                    .fallbackToDestructiveMigration(false)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
