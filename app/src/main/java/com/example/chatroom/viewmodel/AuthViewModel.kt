package com.example.chatroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroom.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.example.chatroom.data.Result
import kotlinx.coroutines.launch

//We need to initialize the userRepository which is dependent on both the FirebaseAuth and
// FirestoreDatabase. For the auth instance we can call it directly within the class without
// having to provide a global access to it because this is the only class we would need it.
// For the Firestore we will need to create an object class which can make it accessible to other classes that will be dependent on it.


class AuthViewModel : ViewModel() {
    private val userRepository : UserRepository


    init{
        userRepository = UserRepository(
                FirebaseAuth.getInstance(),
        Injection.instance())
    }

    private val _authResult  = MutableLiveData<Result<Boolean>>()

    val authResult: LiveData<Result<Boolean>> get() = _authResult


    fun signUp(email: String, password: String, name: String){
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, name)
        }
    }

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            _authResult.value = userRepository.signIn(email, password)
        }
    }

}