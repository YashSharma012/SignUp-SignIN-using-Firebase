 package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

 class MainActivity : AppCompatActivity() {
     lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etUsername= findViewById<TextInputEditText>(R.id.etUsername)
        val etMail= findViewById<TextInputEditText>(R.id.etMail)
        val etPassword= findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignup = findViewById<Button>(R.id.btnCreateAccount)
        val btnSignin = findViewById<Button>(R.id.btnLogin)
        btnSignup.setOnClickListener {
            val uniqueId = etUsername.text.toString()
            val name = etName.text.toString()
            val emailId = etMail.text.toString()
            val password = etPassword.text.toString()

            val user = User(name, emailId, password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        btnSignin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}