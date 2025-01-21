package com.suonk.taskplanner.model.data.entities.user

import com.google.firebase.Timestamp

data class UserEntity(
    val id: String,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val email: String,
    val photoUrl: String,
    val creationDate: Timestamp
)
