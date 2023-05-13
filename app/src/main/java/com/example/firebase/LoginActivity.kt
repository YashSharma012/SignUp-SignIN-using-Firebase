package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    lateinit var databaseReference : DatabaseReference
    companion object{
        const val KEY = "com.example.firebase.LoginActivity.email"
        const val KEY1 = "com.example.firebase.LoginActivity.name"
        const val KEY2 = "com.example.firebase.LoginActivity.userId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputEditText>(R.id.etUsername)
        val signInBtn = findViewById<Button>(R.id.btnLogin)

        signInBtn.setOnClickListener {
            val uniqueId = username.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {
            //if user exist or not
            if(it.exists()){
                //welcome user in your app
                val email = it.child("emailId").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                val intentWelcome = Intent(this, WelcomeScreen::class.java)
                intentWelcome.putExtra(KEY, email.toString())
                intentWelcome.putExtra(KEY1, name.toString())
                intentWelcome.putExtra(KEY2, userId.toString())
                startActivity(intentWelcome)
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}