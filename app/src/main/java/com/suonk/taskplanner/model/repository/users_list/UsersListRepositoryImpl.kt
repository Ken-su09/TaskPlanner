package com.suonk.taskplanner.model.repository.users_list

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.suonk.taskplanner.model.data.entities.user.CustomFirebaseUser
import com.suonk.taskplanner.model.data.entities.user.UserEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersListRepositoryImpl @Inject constructor(firebaseFirestore: FirebaseFirestore) : UsersListRepository {

    private val allUsersCollection = firebaseFirestore.collection(ALL_USERS)

    override fun addUserToFirestore(id: String, userToAdd: UserEntity) {
        allUsersCollection.whereEqualTo(ID, id).whereEqualTo(EMAIL, userToAdd.email).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result.isEmpty) {
                    allUsersCollection.document(id).set(userToAdd)
                }
            }
        }
    }

    override fun getListOfUsers(): Flow<List<UserEntity>> = callbackFlow {
        val listenerRegistration = allUsersCollection.addSnapshotListener { querySnapshot, error ->
            if (querySnapshot != null) {
                try {
                    trySend(querySnapshot.toObjects(UserEntity::class.java))
                } catch (e: Exception) {
                    Log.i("FirebaseError", "Exception : $e")
                }
            }
        }

        awaitClose { listenerRegistration.remove() }
    }

    companion object {
        private const val ALL_USERS = "ALL_USERS"
        private const val ID = "id"
        private const val EMAIL = "email"
    }
}