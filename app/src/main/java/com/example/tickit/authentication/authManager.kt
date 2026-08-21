package com.example.tickit.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class authManager {

    private var auth  = FirebaseAuth.getInstance()

    fun currentUser() : FirebaseUser?{
        return auth.currentUser
    }


    fun currentUserId() : String?{
        return auth.currentUser?.uid
    }


    fun isLogged() : Boolean{
        return auth.currentUser!=null
    }

    fun signOut(){
        auth.signOut()
    }

}