package com.example.parksmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UsersDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_dashboard)

        val button = findViewById<Button>(R.id.users_btn)

        button.setOnClickListener {

            val intent = Intent(this,UserList::class.java)
            startActivity(intent)
        }
    }


}