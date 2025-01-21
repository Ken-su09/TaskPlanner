package com.suonk.taskplanner.ui.auth.welcome

import android.os.Bundle
import android.view.View
import com.suonk.taskmaster.utils.viewBinding
import com.suonk.taskplanner.utils.BaseFragment
import com.suonk.taskplanner.R
import com.suonk.taskplanner.databinding.FragmentWelcomeBinding
import com.suonk.taskplanner.ui.auth.sign_in.SignInFragment
import com.suonk.taskplanner.ui.auth.sign_up.SignUpFragment

class WelcomeFragment : BaseFragment(R.layout.fragment_welcome) {

    private val binding by viewBinding(FragmentWelcomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container_view,
                SignInFragment(),
                "SignInFragment"
            )
                .addToBackStack(null)
                .commit()
        }
        binding.signUpButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container_view,
                SignUpFragment(),
                "SignUpFragment"
            )
                .addToBackStack(null)
                .commit()
        }
    }
}