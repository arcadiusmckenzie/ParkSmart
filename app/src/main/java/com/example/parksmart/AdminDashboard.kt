package com.example.parksmart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val button = findViewById<Button>(R.id.slots_btn)
        button.setOnClickListener{
            val intent = Intent(this, ParkingActivity::class.java)
            startActivity(intent)
        }

//        val button2 = findViewById<Button>(R.id.users_btn)
//        button2.setOnClickListener{
//            val intent = Intent(this, UsersActivity::class.java)
//            startActivity(intent)
//        }

//        val button1 = findViewById<Button>(R.id.display_btn)
//        button1.setOnClickListener{
//            val intent = Intent(this, UsersActivity::class.java)
//            startActivity(intent)
//        }
    }


}