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
import com.importantconcept.notesapp.databinding.FragmentRegisterBinding
import com.importantconcept.notesapp.models.signup.UserRequest
import com.importantconcept.notesapp.utils.NetworkResult
import com.importantconcept.notesapp.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    //view binding: jitna bhi hamara layouts honga usko corresponding ak binding class generate kar di jaya gi.
    //Create binding object
    private var _binding: FragmentRegisterBinding? = null //nullable type
    private val binding get() = _binding!! //null safe

    private val authViewModel by viewModels<AuthViewModel>() //kotlin extension - its calling viewmodelprovider

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        if (tokenManager.getToken() != null){
            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val validateResult = validateUserInput()
            if (validateResult.first){
                authViewModel.registerUser(getUserRequest())
                //findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
            }
            else{
                binding.txtError.text = validateResult.second
            }
        }

        binding.btnLogin.setOnClickListener {
            //authViewModel.loginUser(UserRequest("test10@gmail.com", "test", "test"))
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        bindObservers()

    }

    private fun getUserRequest() : UserRequest{
        val username = binding.txtUsername.text.toString()
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        return UserRequest(emailAddress, password, username)
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        return authViewModel.validateCredentials(userRequest.username, userRequest.email, userRequest.password, false)
    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data!!.token)
                    findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
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