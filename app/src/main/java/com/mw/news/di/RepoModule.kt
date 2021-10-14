package com.mw.news.di

import com.mw.news.network.IArticleApiService
import com.mw.news.repo.ArticlesRemoteRepo
import com.mw.news.repo.ArticlesRemoteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideArticleRepo(api: IArticleApiService): ArticlesRemoteRepo =
        ArticlesRemoteRepoImpl(api)
}