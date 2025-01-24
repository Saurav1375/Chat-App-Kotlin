package com.example.chatroom.viewmodel
//We will call this class injection just because we are trying to prove a single instance of Firestore
// which is part of what Dependency Injection does. Now back to the AuthViewodel class we can now create
// an instance of UserRepository within the init block providing its dependencies.object Injection

import com.google.firebase.firestore.FirebaseFirestore

object Injection{
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    fun instance(): FirebaseFirestore {
        return instance
    }
}