package com.suonk.taskplanner.model.repository.user

import com.google.firebase.auth.FirebaseAuth
import com.suonk.taskplanner.model.data.entities.user.CustomFirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val auth: FirebaseAuth) {

    fun getCustomFirebaseUser(): CustomFirebaseUser? {
        val currentUser = auth.currentUser
        return if (currentUser != null) {
            CustomFirebaseUser(
                uid = currentUser.uid,
                displayName = currentUser.displayName ?: "",
                email = currentUser.email ?: "",
                photoUrl = currentUser.photoUrl?.toString() ?: ""
            )
        } else {
            null
        }
    }
}