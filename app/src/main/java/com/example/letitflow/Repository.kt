package com.example.letitflow

interface Repository {
    suspend fun fetch(id: Long): User
}