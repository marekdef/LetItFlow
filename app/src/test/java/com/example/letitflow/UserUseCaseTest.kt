package com.example.letitflow

import com.example.letitflow.Resource.Loading
import com.example.letitflow.Resource.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class UserUseCaseTest {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val repository = object : Repository {
        override suspend fun fetch(id: Long): User = User(id)
    }

    private val id = Random.nextLong()
    private val result = mutableListOf<Resource<User>>()

    private val useCase =  UserUseCase(repository)

    @OptIn(ExperimentalStdlibApi::class)
    @ExperimentalCoroutinesApi
    @org.junit.jupiter.api.Test
    fun testExecute6() = runBlockingTest {
        val useCase = UserUseCase(repository, this.coroutineContext[CoroutineDispatcher.Key]!!)
        useCase.execute(id).toList(result)

        assertTrue(result.size == 2)
        assertEquals(result[0], Loading<User>())
        assertEquals(result[1], Success(User(id)))
    }


    @ExperimentalCoroutinesApi
    @org.junit.jupiter.api.Test
    fun testExecute5() = testCoroutineDispatcher.runBlockingTest {
        val useCase = UserUseCase(repository, testCoroutineDispatcher)
        useCase.execute(id).toList(result)

        assertTrue(result.size == 2)
        assertEquals(result[0], Loading<User>())
        assertEquals(result[1], Success(User(id)))
    }

    @ExperimentalCoroutinesApi
    @org.junit.jupiter.api.Test
    fun testExecute4() = runBlockingTest {
        val useCase = UserUseCase(repository, testCoroutineDispatcher)
        useCase.execute(id).toList(result)

        assertTrue(result.size == 2)
        assertEquals(result[0], Loading<User>())
        assertEquals(result[1], Success(User(id)))
    }



    @ExperimentalCoroutinesApi
    @org.junit.jupiter.api.Test
    fun testExecute3() = runBlocking {
        useCase.execute(id).toList(result)

        assertTrue(result.size == 2)
        assertEquals(result[0], Loading<User>())
        assertEquals(result[1], Success(User(id)))
    }

    @ExperimentalCoroutinesApi
    @org.junit.jupiter.api.Test
    fun testExecute2() = testCoroutineDispatcher.runBlockingTest {
        useCase.execute(id).toList(result)

        assertTrue(result.size == 2)
        assertEquals(result[0], Loading<User>())
        assertEquals(result[1], Success(User(id)))
    }

    @ExperimentalCoroutinesApi
    @org.junit.jupiter.api.Test
    fun testExecute() = runBlockingTest {
        useCase.execute(id).toList(result)

        assertTrue(result.size == 2)
        assertEquals(result[0], Loading<User>())
        assertEquals(result[1], Success(User(id)))
    }
}