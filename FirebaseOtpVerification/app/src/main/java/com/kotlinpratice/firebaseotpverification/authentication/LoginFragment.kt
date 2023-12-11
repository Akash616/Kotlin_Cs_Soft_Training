package com.kotlinpratice.firebaseotpverification.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kotlinpratice.firebaseotpverification.R
import com.kotlinpratice.firebaseotpverification.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.fabLogin.setOnClickListener {
            val number = binding.etPhoneNumber.text
            val password = binding.etPassword.text
            if (number.trim().isEmpty() && password.trim().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter all details", Toast.LENGTH_SHORT)
                    .show()
            } else if (number.trim().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter number", Toast.LENGTH_SHORT).show()
            } else if (password.trim().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter password", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
            }
        }

        binding.btnNewUser.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

    }

}