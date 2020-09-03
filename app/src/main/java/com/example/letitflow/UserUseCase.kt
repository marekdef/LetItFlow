package com.example.letitflow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber

@ExperimentalCoroutinesApi
class UserUseCase(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun execute(id: Long) = flow<Resource<User>> {
        println("execute($id).flow ${Thread.currentThread().name}")
        Timber.d("execute($id).flow ${Thread.currentThread().name}")
        emit(Resource.Success(repository.fetch(id)))
    }.catch { ex -> emit(Resource.Error(ex)) }
        .onStart {
            println("execute($id).onStart ${Thread.currentThread().name}")
            Timber.d("execute($id).onStart ${Thread.currentThread().name}")
            emit(Resource.Loading())
        }
        .flowOn(coroutineDispatcher)
        .onEach { resource ->
            println("execute($id).onEach - $resource - ${Thread.currentThread().name}")
            Timber.d("execute($id).onEach - $resource - ${Thread.currentThread().name}")
        }.onCompletion {
            println("execute($id).onCompletion - ${Thread.currentThread().name}")
            Timber.d("execute($id).onEach - ${Thread.currentThread().name}")
        }
}