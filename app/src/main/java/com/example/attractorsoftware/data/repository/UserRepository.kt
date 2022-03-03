package com.example.attractorsoftware.data.repository

import android.content.Context
import com.example.attractorsoftware.data.models.PersonModel
import com.example.attractorsoftware.server.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {
    private val client: UserData = UserData()
    fun getUserData(context: Context): Flow<List<PersonModel>> = flow {
        emit(client.getUserData(context))
    }
}