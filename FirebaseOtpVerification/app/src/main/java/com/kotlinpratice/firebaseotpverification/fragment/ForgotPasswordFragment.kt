package com.kotlinpratice.firebaseotpverification.fragment

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.kotlinpratice.firebaseotpverification.R
import com.kotlinpratice.firebaseotpverification.databinding.FragmentForgotPasswordBinding
import java.util.concurrent.TimeUnit

class ForgotPasswordFragment : Fragment() {

    lateinit var binding: FragmentForgotPasswordBinding
    lateinit var phone_number: String
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

//        val prefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val flag = prefs.getBoolean("flag", false)
//        if (flag){
//            findNavController().navigate(R.id.action_forgotPasswordFragment_to_homeScreenFragment)
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabForgotPassword.setOnClickListener {
            if (!binding.etPhoneNumber.text.toString().trim().isEmpty()) {
                val country_code = "+" + binding.ccpForgotPass.selectedCountryCode.trim()
                phone_number = country_code + binding.etPhoneNumber.text.toString().trim();
                Log.d("Ph", phone_number)
                binding.pbForgotPass.visibility = View.VISIBLE
                //----Firebase---------------------------------
                sendVerificationCode(phone_number)
                //findNavController().navigate(R.id.action_forgotPasswordFragment_to_otpVerificationFragment)
            } else {
                Toast.makeText(requireContext(), "Please enter mobile number", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    private fun sendVerificationCode(phoneNumber: String) {
        auth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks: OnVerificationStateChangedCallbacks = object  : OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            binding.pbForgotPass.visibility = View.GONE
            Log.d("onVerificationCompleted: ", phoneAuthCredential.toString())
        }

        override fun onVerificationFailed(exception: FirebaseException) {
            binding.pbForgotPass.visibility = View.GONE
            Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
            Log.w("onVerificationFailed: ", exception)
        }

        override fun onCodeSent(backEndOtp: String, forceResendingToken: ForceResendingToken) {
            super.onCodeSent(backEndOtp, forceResendingToken)
            binding.pbForgotPass.visibility = View.GONE
            Log.d("onCodeSent: ", backEndOtp)
            val bundle = Bundle()
            bundle.putString("phone_number", phone_number)
            bundle.putString("backEndOtp", backEndOtp)
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_otpVerificationFragment, bundle)
        }

    }

}