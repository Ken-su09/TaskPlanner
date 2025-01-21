package com.suonk.taskplanner.ui.auth.sign_in

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.suonk.taskmaster.utils.showToast
import com.suonk.taskmaster.utils.viewBinding
import com.suonk.taskplanner.R
import com.suonk.taskplanner.databinding.FragmentSignInBinding
import com.suonk.taskplanner.ui.main.MainActivity
import com.suonk.taskplanner.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewModel by viewModels<SignInViewModel>()
    private val binding by viewBinding(FragmentSignInBinding::bind)

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { idToken ->
                    firebaseAuthWithGoogle(idToken)
                }
            } catch (e: ApiException) {

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.toastMessageSingleLiveEvent.observe(viewLifecycleOwner) { toastMessage ->
            toastMessage.showToast(requireContext())
        }

        viewModel.successfullyLoginSingleLiveEvent.observe(viewLifecycleOwner) { isLoginSuccessful ->
            if (isLoginSuccessful) {
                requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
            }
        }

        onLoginGoogleButtonClick()
    }

    private fun onLoginGoogleButtonClick() {
        binding.googleButton.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task: Task<AuthResult?> ->
            task.isSuccessful
            viewModel.loginWithGoogle(idToken)
        }
    }
}