package com.example.parksmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class AdminPanel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

       val cardview1 = findViewById<CardView>(R.id.report_card)
        cardview1.setOnClickListener {
            val intent = Intent (this, UsersDashboard::class.java )
            startActivity(intent)
        }
        val cardview2 = findViewById<CardView>(R.id.slots_card)
        cardview2.setOnClickListener {
            val intent = Intent (this, AdminDashboard::class.java )
            startActivity(intent)
        }
//        val cardview3 = findViewById<CardView>(R.id.firebase_card)
//        cardview3.setOnClickListener {
//            val intent = Intent (this, FirstActivity::class.java )
//            startActivity(intent)
//        }
        val cardview4 = findViewById<CardView>(R.id.logout_card)
        cardview4.setOnClickListener {
            val intent = Intent (this, FirstActivity::class.java )
            startActivity(intent)
        }


    }
}