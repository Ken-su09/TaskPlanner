package com.suonk.taskplanner.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.suonk.taskmaster.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    val isCurrentUserLogin = SingleLiveEvent<Boolean>()

    init {
        isCurrentUserLogin.setValue(firebaseAuth.currentUser == null)
    }
}