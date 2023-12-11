package com.kotlinpratice.firebaseotpverification.authentication

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.kotlinpratice.firebaseotpverification.R
import com.kotlinpratice.firebaseotpverification.databinding.FragmentSignUpBinding
import java.util.Calendar


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    lateinit var arrayList: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>

    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrowBack.setOnClickListener {
            activity?.onBackPressed()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.fabSignUp.setOnClickListener {
            if (!binding.etSignupFName.getText().toString().trim()
                    .isEmpty() && !binding.etSignupLName.getText().toString().trim()
                    .isEmpty() && !binding.etSignupNumber.getText().toString().trim()
                    .isEmpty() && !binding.etSignupEmail.getText().toString().trim()
                    .isEmpty() && !binding.etSignupDob.getText().toString().trim()
                    .isEmpty() && !binding.etSignupPassword.getText().toString().trim()
                    .isEmpty() && !binding.etSignupConfirmPass.getText().toString().trim()
                    .isEmpty()
            ) {

                if (binding.spinnerSignupGender.getSelectedItem().toString().trim() == "Gender") {
                    Toast.makeText(requireContext(), "Please select Gender", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (binding.etSignupPassword.getText()
                            .toString() == binding.etSignupConfirmPass.getText().toString()
                    ) {
                        if (binding.cbSignup.isChecked()) {
                            binding.llSignup.setVisibility(View.INVISIBLE)
                            binding.pbSignup.setVisibility(View.VISIBLE)
                            findNavController().navigate(R.id.action_signUpFragment_to_homeScreenFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Please agree terms and conditions",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Password doesn't match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Please enter all details", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        arrayList = java.util.ArrayList()
        arrayList.add("Gender")
        arrayList.add("Male")
        arrayList.add("Female")
        arrayList.add("Others")
        adapter = ArrayAdapter<String>(requireActivity(), R.layout.spinner_dropdown_style, arrayList)
        //adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        binding.spinnerSignupGender.setAdapter(adapter)

        binding.spinnerSignupGender.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

                //Selected Spinner item color change.
                val s = parent?.selectedItem
                Log.d("spinner: ",s.toString())
                if (s != null) {
                    if (s.equals("Gender")){
                        (parent!!.getChildAt(0) as TextView).setTextColor(Color.GRAY) //gray
                    }else{
                        (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLACK) //black
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })


        binding.etSignupDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val date = calendar.get(Calendar.DAY_OF_MONTH)

//            requireActivity().setTheme(R.style.MyCalendarView)

            val datePickerDialog = DatePickerDialog(
                requireActivity(), { view, year, month, dayOfMonth ->
                    binding.etSignupDob.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
                }, year, month, date
            )
            datePickerDialog.show()

        }

        binding.ivGoogleSignIn.setOnClickListener {
            firebaseAuthWithGoogle()
        }

    }

    private fun firebaseAuthWithGoogle() {

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions)

        val intent: Intent = googleSignInClient.signInIntent
        registerForResult.launch(intent)

    }

    val registerForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK && it != null) {
                val signInAccountTask: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(it.data)
                if (signInAccountTask.isSuccessful) {
                    val s = "Google sign in successful"
                    displayToast(s)
                    handleSignInResult(signInAccountTask)
                }
            }

        }

    private fun handleSignInResult(signInAccountTask: Task<GoogleSignInAccount>) {
        try {

            val googleSignInAccount: GoogleSignInAccount = signInAccountTask.getResult(ApiException::class.java)
            if (googleSignInAccount != null) {
                val authCredential : AuthCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
                //check credential
                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(requireActivity()){
                    if (it.isSuccessful){
                        displayToast("Firebase authentication successful")

                        firebaseUser = firebaseAuth.currentUser!!
                        Log.d("firebaseUser: ", firebaseUser.toString())
                        if (firebaseUser!=null){
                            val prefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            val editor = prefs.edit()
                            editor.putString("firebaseUser", firebaseUser.toString())
                            editor.apply()
                            findNavController().navigate(R.id.action_signUpFragment_to_homeScreenFragment)
                        }

                    }else{
                        displayToast("Authentication Failed: "+ it.exception!!.message)
                    }
                }
            }

        } catch (e: ApiException) {
            Log.d("SignIn: ", e.statusCode.toString())
        }
    }

    private fun displayToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

}