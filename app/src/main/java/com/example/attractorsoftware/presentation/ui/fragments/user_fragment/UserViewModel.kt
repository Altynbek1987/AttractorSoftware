package com.example.attractorsoftware.presentation.ui.fragments.user_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attractorsoftware.data.models.PersonModel
import com.example.attractorsoftware.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserViewModel: ViewModel(){
    private val repository: UserRepository = UserRepository()

    private val _userData = MutableStateFlow<List<PersonModel>>(emptyList())
    val message: StateFlow<List<PersonModel>> = _userData

    fun getUserData(context: Context) {
        viewModelScope.launch {
            repository.getUserData(context).collectLatest {
                _userData.value = it
            }
        }
    }
}