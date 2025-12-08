package com.rudra.titangymmanager.di

import android.content.Context
import com.rudra.titangymmanager.data.AppDatabase
import com.rudra.titangymmanager.data.dao.ExpenseDao
import com.rudra.titangymmanager.data.dao.MemberDao
import com.rudra.titangymmanager.data.dao.MembershipPackageDao
import com.rudra.titangymmanager.data.dao.PaymentDao
import com.rudra.titangymmanager.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMemberDao(appDatabase: AppDatabase): MemberDao {
        return appDatabase.memberDao()
    }

    @Provides
    @Singleton
    fun provideMembershipPackageDao(appDatabase: AppDatabase): MembershipPackageDao {
        return appDatabase.membershipPackageDao()
    }

    @Provides
    @Singleton
    fun providePaymentDao(appDatabase: AppDatabase): PaymentDao {
        return appDatabase.paymentDao()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(appDatabase: AppDatabase): ExpenseDao {
        return appDatabase.expenseDao()
    }

    @Provides
    @Singleton
    fun provideMemberRepository(memberDao: MemberDao): MemberRepository {
        return MemberRepositoryImpl(memberDao)
    }

    @Provides
    @Singleton
    fun provideMembershipPackageRepository(membershipPackageDao: MembershipPackageDao): MembershipPackageRepository {
        return MembershipPackageRepositoryImpl(membershipPackageDao)
    }

    @Provides
    @Singleton
    fun providePaymentRepository(paymentDao: PaymentDao): PaymentRepository {
        return PaymentRepositoryImpl(paymentDao)
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(expenseDao: ExpenseDao): ExpenseRepository {
        return ExpenseRepositoryImpl(expenseDao)
    }
}
