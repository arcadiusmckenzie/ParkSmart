package com.example.parksmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val button = findViewById<Button>(R.id.home_login)
        button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.homeSignup)
        button2.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}