package com.mw.news.base

interface BaseUseCaseWithParams<P, R> {

    suspend fun run(params: P): R

}

interface BaseUseCaseWithoutParams<R> {

    suspend fun run(): R

}