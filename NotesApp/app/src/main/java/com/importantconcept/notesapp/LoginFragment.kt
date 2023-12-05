package com.importantconcept.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.importantconcept.notesapp.databinding.FragmentLoginBinding
import com.importantconcept.notesapp.models.signup.UserRequest
import com.importantconcept.notesapp.utils.NetworkResult
import com.importantconcept.notesapp.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null //nullable type
    private val binding get() = _binding!! //null safe
    private val authViewModel by viewModels<AuthViewModel>() //kotlin extensions

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val validateResult = validateUserInput()
            if (validateResult.first){
                authViewModel.loginUser(getUserRequest())
                //findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
            }
            else{
                binding.txtError.text = validateResult.second
            }
        }

        binding.btnSignUp.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment_registerFragment) // ERROR IMPORTANT CONCEPT
            findNavController().popBackStack() //pop top item (LoginFragment) //Solution
        }

        bindObservers()

    }

    private fun getUserRequest() : UserRequest {
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        return UserRequest(emailAddress, password, "")
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        return authViewModel.validateCredentials(userRequest.username, userRequest.email, userRequest.password, true)
    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data!!.token)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }

                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }


    override fun onDestroyView() { //for performance
        super.onDestroyView()
        _binding = null
    }

}