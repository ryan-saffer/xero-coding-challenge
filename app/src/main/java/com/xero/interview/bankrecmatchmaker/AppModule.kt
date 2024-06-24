package com.xero.interview.bankrecmatchmaker

import com.xero.interview.bankrecmatchmaker.RecMatcher.repositories.RecRepository
import com.xero.interview.bankrecmatchmaker.RecMatcher.repositories.RecRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecRepository(): RecRepository {
        return RecRepositoryImpl()
    }
}