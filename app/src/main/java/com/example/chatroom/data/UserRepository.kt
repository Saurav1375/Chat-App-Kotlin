package com.example.chatroom.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth: FirebaseAuth,        // FirebaseAuth instance for user authentication
    private val firestore: FirebaseFirestore // FirebaseFirestore instance for handling Firestore database
) {
    suspend fun signUp(email: String, password: String, name: String): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await() //authenticate user with email and password
            val user = User(name, email)

            //Add user to firestore
            saveUserToFirestore(user)

            Result.Success(true) // Return success with the created user


        } catch (e: Exception) {
            Result.Error(e)

        }


    suspend fun signIn(email: String, password: String) : Result<Boolean> =

        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        }
        catch (e: Exception){
            Result.Error(e)
        }


    private suspend fun saveUserToFirestore(user: User) {
        // firestore saves data as documents within a collection. For each user document we will save
        //them in a collection called users providing their email as a key rather than letting
        // firestore generate random characters. With this email we can then manage each user better in the chatrooms.
        firestore.collection("users").document(user.email).set(user).await()
    }


    suspend fun getCurrentUser(): Result<User> {
        return try {
            // Get the currently authenticated user from FirebaseAuth
            val firebaseUser: FirebaseUser? = auth.currentUser
            firebaseUser?.let {
                // Fetch the user document from Firestore using the email as the document key
                val userDocument = firestore.collection("users").document(it.email!!).get().await()
                if (userDocument.exists()) {
                    // Map the Firestore document to the User data class
                    val user = userDocument.toObject(User::class.java)
                    user?.let {it->
                        Result.Success(it) // Return the fetched user wrapped in Success
                    } ?: Result.Error(Exception("User data is null")) // Handle null case
                } else {
                    Result.Error(Exception("User does not exist in Firestore"))
                }
            } ?: Result.Error(Exception("No authenticated user found"))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}

