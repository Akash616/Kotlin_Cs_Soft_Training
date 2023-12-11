package com.kotlinpratice.firebaseotpverification

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.kotlinpratice.firebaseotpverification.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //getting saved data from sharedpref.
        val pref = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val currentUser: String? = pref.getString("firebaseUser", null)
        Log.d("Current User: ", currentUser.toString())
        if (currentUser!=null){
            findNavController().navigate(R.id.action_splashScreenFragment_to_homeScreenFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
        }, 2000)

    }

}