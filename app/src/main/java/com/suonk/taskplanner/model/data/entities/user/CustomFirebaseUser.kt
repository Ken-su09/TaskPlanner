package com.suonk.taskplanner.model.data.entities.user

data class CustomFirebaseUser(
    val uid: String,
    val displayName: String,
    val email: String,
    val photoUrl: String
)