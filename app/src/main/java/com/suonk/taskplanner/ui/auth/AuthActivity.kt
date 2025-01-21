package com.suonk.taskplanner.ui.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suonk.taskplanner.R
import com.suonk.taskplanner.databinding.ActivityAuthBinding
import com.suonk.taskplanner.ui.auth.welcome.WelcomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container_view,
            WelcomeFragment(),
            "WelcomeFragment"
        )
            .addToBackStack(null)
            .commit()
    }
}