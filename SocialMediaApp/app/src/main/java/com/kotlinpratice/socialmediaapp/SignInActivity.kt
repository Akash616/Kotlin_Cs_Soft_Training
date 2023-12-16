package com.kotlinpratice.socialmediaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.kotlinpratice.socialmediaapp.daos.UserDao
import com.kotlinpratice.socialmediaapp.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class SignInActivity : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 123
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser

    lateinit var signInButton: SignInButton
    lateinit var progressbar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInButton = findViewById(R.id.signInButton)
        progressbar = findViewById(R.id.progressbar)

        firebaseAuthWithGoogle()
        firebaseAuth = Firebase.auth

        signInButton.setOnClickListener {
            signIn()
        }

    }

    override fun onStart() {
        super.onStart() //If user is already signIn.
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun firebaseAuthWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(signInAccountTask)
        }
    }

    private fun handleSignInResult(signInAccountTask: Task<GoogleSignInAccount>) {
        try {
            val googleSignInAccount: GoogleSignInAccount =
                signInAccountTask.getResult(ApiException::class.java)
            firebaseWithGoogle(googleSignInAccount.idToken)
        } catch (e: ApiException) {
            Log.d("SignIn: ", e.statusCode.toString())
        }
    }

    private fun firebaseWithGoogle(idToken: String?) {
        val authCredential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        signInButton.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
        //Coroutine
        GlobalScope.launch(Dispatchers.IO) {
            val auth = firebaseAuth.signInWithCredential(authCredential).await()
            firebaseUser = auth.user!!
            withContext(Dispatchers.Main) {//Switch to Main Thread
                updateUI(firebaseUser)
            }
        }
    }

    private fun updateUI(firebaseUser: FirebaseUser) {
        if (firebaseUser != null) {

            //--User add in Firestore Database------------------------------------------------------------
            val user = User(firebaseUser.uid, firebaseUser.displayName.toString(),
                firebaseUser.photoUrl.toString())
            val userDao = UserDao()
            userDao.addUser(user)
            //------------------------------------------------------------------------------------

            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        } else {
            signInButton.visibility = View.VISIBLE
            progressbar.visibility = View.GONE
            Toast.makeText(this, "SignIn failed", Toast.LENGTH_SHORT).show()
        }
    }


}