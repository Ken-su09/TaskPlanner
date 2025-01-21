package com.suonk.taskplanner.ui.auth.sign_in

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.suonk.taskmaster.utils.NativeText
import com.suonk.taskmaster.utils.SingleLiveEvent
import com.suonk.taskplanner.domain.users.AddNewUserToFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val addNewUserToFirestoreUseCase: AddNewUserToFirestoreUseCase
) : ViewModel() {

    val toastMessageSingleLiveEvent = SingleLiveEvent<NativeText>()
    val successfullyLoginSingleLiveEvent = SingleLiveEvent<Boolean>()

    fun loginWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let { currentUser ->
                        addNewUserToFirestoreUseCase.invoke(currentUser.uid)
//                        onResult(true, null)
//                        toastMessageSingleLiveEvent.setValue(NativeText.Resource(R.string.sign_in_successfull_toast))
                        successfullyLoginSingleLiveEvent.setValue(true)
                    }
                } else {
//                    toastMessageSingleLiveEvent.setValue(NativeText.Resource(R.string.sign_in_failed_toast))
                    successfullyLoginSingleLiveEvent.setValue(true)
                }
            }
    }
}