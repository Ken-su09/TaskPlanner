package com.suonk.taskplanner.model.repository.users_list

import com.suonk.taskplanner.model.data.entities.user.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersListRepository {

    fun addUserToFirestore(id: String, userToAdd: UserEntity)

    fun getListOfUsers(): Flow<List<UserEntity>>
}