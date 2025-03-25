package com.iec.makeup.data.repository

import javax.inject.Inject

interface AuthRepository {
    suspend fun doLogin(username: String, password: String)
    suspend fun doRegistration(username: String, password: String)
}

class AuthRepositoryImpl @Inject constructor(

) : AuthRepository {
    override suspend fun doLogin(username: String, password: String) {

    }

    override suspend fun doRegistration(username: String, password: String) {

    }

}