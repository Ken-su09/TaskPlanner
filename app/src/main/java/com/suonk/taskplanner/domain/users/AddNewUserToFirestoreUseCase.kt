package com.suonk.taskplanner.domain.users

import com.google.firebase.Timestamp
import com.suonk.taskplanner.model.data.entities.user.UserEntity
import com.suonk.taskplanner.model.repository.user.UserRepository
import com.suonk.taskplanner.model.repository.users_list.UsersListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddNewUserToFirestoreUseCase @Inject constructor(
    private val usersListRepository: UsersListRepository,
    private val userRepository: UserRepository
) {

    fun invoke(uid: String) {
        userRepository.getCustomFirebaseUser()?.let {

            usersListRepository.addUserToFirestore(
                uid,
                UserEntity(
                    id = it.uid,
                    firstName = "",
                    lastName = "",
                    displayName = it.displayName,
                    email = it.email,
                    photoUrl = it.photoUrl,
                    creationDate = Timestamp.now()
                )
            )
        }
    }
}