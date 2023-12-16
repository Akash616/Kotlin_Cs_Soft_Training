package com.kotlinpratice.socialmediaapp.daos

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.kotlinpratice.socialmediaapp.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    //Is dao ka kaam hoga apka, user(databse) ka andar user ki entry ko dalna.

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")

    fun addUser(user: User){
        user?.let { //if user is not null then go inside block
            GlobalScope.launch(Dispatchers.IO) {
                userCollection.document(user.userId).set(it)
                //operation in background thread
            }
        }
    }

    fun getUserById(uId: String): Task<DocumentSnapshot>{
        return userCollection.document(uId).get()
    }

}