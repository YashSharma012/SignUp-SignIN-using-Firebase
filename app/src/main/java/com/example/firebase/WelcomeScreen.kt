package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DatabaseReference

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val name = intent.getStringExtra(LoginActivity.KEY1)
        val mail = intent.getStringExtra(LoginActivity.KEY)
        val userId = intent.getStringExtra(LoginActivity.KEY2)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        welcomeText.text = "Welcome $name"

        val email = findViewById<Button>(R.id.btnEmail)
        email.text = "Mail : $mail"

        val username = findViewById<Button>(R.id.btnUniqueId)
        username.text = "UserId : $userId"
    }
}