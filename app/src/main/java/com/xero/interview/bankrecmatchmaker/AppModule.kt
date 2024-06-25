package com.xero.interview.bankrecmatchmaker

import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecRepository
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecRepositoryImpl
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecAutoMatcherRepository
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecAutoMatcherRepositoryImpl
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

    @Provides
    @Singleton
    fun provideRecoAutoMatcherRepository(): RecAutoMatcherRepository {
        return RecAutoMatcherRepositoryImpl()
    }
}