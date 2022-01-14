package com.example.parksmart

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import android.graphics.Color.RED

import android.graphics.drawable.ColorDrawable
import android.widget.Chronometer


class ParkingActivity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var database1:FirebaseDatabase
    private lateinit var ref: DatabaseReference
    var dref: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)

        database1 = FirebaseDatabase.getInstance()
        ref = database1.getReference("parking")



        val row1 = findViewById<View>(R.id.tableRow1)
            dref = FirebaseDatabase.getInstance().reference.child("Distance")
            dref!!.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var distance = snapshot.getValue<Long>()
                    Log.i(TAG, "Distance is: $distance")
                    if (distance != null) {
                        if (distance<50){

                            row1.setBackgroundColor(RED)
                        }
                    }

                    val color = (row1.background as ColorDrawable).color
                    Log.i(TAG, "color is: $color")

                    if (color >= -65536){

                        val simpleChronometer =
                            findViewById<View>(R.id.simpleChronometer) as Chronometer // initiate a chronometer


                        simpleChronometer.start() // start a chronometer


                    }



                }

                override fun onCancelled(error: DatabaseError) {}
            })





        //row1.setOnClickListener { onClick(row1) }
        val row2 = findViewById<View>(R.id.tableRow2)
        row2.setOnClickListener { onClick(row2) }
        val row3 = findViewById<View>(R.id.tableRow3)
        row3.setOnClickListener { onClick(row3) }
        val row4 = findViewById<View>(R.id.tableRow4)
        row4.setOnClickListener { onClick(row4) }
        val row5 = findViewById<View>(R.id.tableRow5)
        row5.setOnClickListener { onClick(row5) }
        val row6 = findViewById<View>(R.id.tableRow6)
        row6.setOnClickListener { onClick(row6) }
        val row7 = findViewById<View>(R.id.tableRow7)
        row7.setOnClickListener { onClick(row7) }
        val row8 = findViewById<View>(R.id.tableRow8)
        row8.setOnClickListener { onClick(row8) }
        val row9 = findViewById<View>(R.id.tableRow9)
        row9.setOnClickListener { onClick(row9) }
        val row10 = findViewById<View>(R.id.tableRow10)
        row10.setOnClickListener { onClick(row10) }










//        dref!!.addValueEventListener(object: ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = snapshot.child("Distance:").getValue<String>()
//
//                Log.i(TAG, "Value is: $value")
//
//                if (value != null) {
//                    if(value < 50.toString()){
//                        findViewById<TableRow>(R.id.tableRow1).setBackgroundColor(Color.RED)
//
//                    }
//                }
//
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//
//        })
    }




    


    override fun onClick(v: View) {
        when (v.id) {
            R.id.tableRow1 -> {
//                v.setBackgroundColor(Color.RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow2 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow3 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow4 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }R.id.tableRow5 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow6 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow7 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow8 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow9 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.tableRow10 -> {
                v.setBackgroundColor(RED)
                run {
                    val intent = Intent(this, BookActivity::class.java)
                    startActivity(intent)

                }
            }

        }


    }

}