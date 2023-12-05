package com.kotlinpratice.firebaseotpverification.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.kotlinpratice.firebaseotpverification.R
import com.kotlinpratice.firebaseotpverification.databinding.FragmentOtpVerificationBinding
import java.util.concurrent.TimeUnit

class OtpVerificationFragment : Fragment() {

    lateinit var binding: FragmentOtpVerificationBinding
    lateinit var getBackEndOtp : String
    lateinit var phone_number : String
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)
        getBackEndOtp = arguments?.getString("backEndOtp").toString()
        phone_number = arguments?.getString("phone_number").toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabVerifyPassword.setOnClickListener {
            if (!binding.etInputOtp1.text.toString().trim().isEmpty() && !binding.etInputOtp2.text.toString().trim().isEmpty() &&
                !binding.etInputOtp3.text.toString().trim().isEmpty() && !binding.etInputOtp4.text.toString().trim().isEmpty() &&
                !binding.etInputOtp5.text.toString().trim().isEmpty() && !binding.etInputOtp6.text.toString().trim().isEmpty()){

                val userEnteredCodeOtp: String = binding.etInputOtp1.text.toString() +
                        binding.etInputOtp2.text.toString() +
                        binding.etInputOtp3.text.toString() +
                        binding.etInputOtp4.text.toString() +
                        binding.etInputOtp5.text.toString() +
                        binding.etInputOtp6.text.toString()

                if (getBackEndOtp != null){
                    binding.pbOtpVerify.visibility = View.VISIBLE
                    val credential = PhoneAuthProvider.getCredential(getBackEndOtp, userEnteredCodeOtp)
                    signInWithPhoneAuthCredential(credential)
                    //findNavController().navigate(R.id.action_otpVerificationFragment_to_homeScreenFragment)
                }else{
                    Toast.makeText(requireContext(), "Otp is null", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(requireContext(), "Please enter all number", Toast.LENGTH_SHORT).show()
            }
        }

        //Automatically cursor move to next
        autoCursorOtpMove()

        binding.btnResendOtp.setOnClickListener {
            resendVerificationCode(phone_number)
        }

    }

    private fun resendVerificationCode(phoneNumber: String) {
        auth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(object : OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    Log.d("onVerificationCompleted: ", phoneAuthCredential.toString())
                }

                override fun onVerificationFailed(firebaseException: FirebaseException) {
                    Toast.makeText(requireContext(), firebaseException.message, Toast.LENGTH_SHORT).show()
                    Log.w("onVerificationFailed: ", firebaseException)
                }

                override fun onCodeSent(newBackendOtp: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(newBackendOtp, p1)
                    getBackEndOtp = newBackendOtp
                    Log.e("NewBackendOtp", "new otp $getBackEndOtp")
                    Toast.makeText(requireContext(), "Otp send successfully", Toast.LENGTH_SHORT).show()
                }

            }) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun autoCursorOtpMove() {

        binding.etInputOtp1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()){
                    binding.etInputOtp2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etInputOtp2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()){
                    binding.etInputOtp3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etInputOtp3.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()){
                    binding.etInputOtp4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etInputOtp4.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()){
                    binding.etInputOtp5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etInputOtp5.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()){
                    binding.etInputOtp6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.etInputOtp6.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.toString().trim().isEmpty()){
                    binding.etInputOtp6.clearFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()){

                binding.pbOtpVerify.visibility = View.GONE
                if (it.isSuccessful){
                    Log.d("signInWithCredential: ", "success")

                    //save in shared pref.
//                    val prefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//                    val editor = prefs.edit()
//                    editor.putBoolean("flag", true)
//                    editor.apply()

                    findNavController().navigate(R.id.action_otpVerificationFragment_to_homeScreenFragment)
                }else{
                    Log.d("signInWithCredential: failure", it.exception.toString())
                    Toast.makeText(requireContext(), "Enter the correct Otp", Toast.LENGTH_SHORT).show()
                }
            }
    }

}