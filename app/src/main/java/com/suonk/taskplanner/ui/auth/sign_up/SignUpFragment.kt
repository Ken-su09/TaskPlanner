package com.suonk.taskplanner.ui.auth.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suonk.taskmaster.utils.viewBinding
import com.suonk.taskplanner.R
import com.suonk.taskplanner.databinding.FragmentSignUpBinding
import com.suonk.taskplanner.databinding.FragmentWelcomeBinding
import com.suonk.taskplanner.utils.BaseFragment

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}