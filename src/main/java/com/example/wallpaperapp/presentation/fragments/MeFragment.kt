package com.example.wallpaperapp.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.wallpaperapp.R
import com.example.wallpaperapp.R.id.navigate_me
import com.example.wallpaperapp.presentation.view.LoginActivity
import com.example.wallpaperapp.presentation.view.UserRegistrationActivity

class MeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_me, container, false)

        val registrationButton = view.findViewById<Button>(R.id.me_registartion_button)

        registrationButton?.setOnClickListener {
            val intent = Intent(activity, UserRegistrationActivity::class.java)
            startActivity(intent)
        }

        val loginButton = view.findViewById<Button>(R.id.me_login_button)
        loginButton?.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}