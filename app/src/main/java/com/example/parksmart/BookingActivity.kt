package com.example.parksmart

import android.content.ContentValues
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.example.parksmart.ParkingInfo
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.parksmart.R
import android.widget.Toast
import com.example.parksmart.PaymentFragment
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.getValue

import android.widget.Chronometer




class BookingActivity : AppCompatActivity() {
    // creating variables for
    // EditText and buttons.
//     var vehicleNumber: EditText? = null
//     var bookingEmail: EditText? = null

    // creating a variable for our
    // Firebase Database.
    var firebaseDatabase: FirebaseDatabase? = null

    // creating a variable for our Database
    // Reference for Firebase.
    var databaseReference: DatabaseReference? = null

    // creating a variable for
    // our object class
    var parkingInfo: ParkingInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

//        var database1 = FirebaseDatabase.getInstance()
//        var ref = database1.getReference("parking")

        val dref = FirebaseDatabase.getInstance().reference.child("Distance")
        dref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val distance = snapshot.getValue<Long>()
                Log.i(ContentValues.TAG, "Distance is: $distance")
                if (distance != null) {
                    if (distance<25){

                        val simpleChronometer =
                            findViewById<View>(R.id.simpleChronometer) as Chronometer // initiate a chronometer


                        simpleChronometer.start() // start a chronometer


                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {}
        })

        // initializing our edittext and button
       val vehicleNumber = findViewById<EditText>(R.id.vehicle_id)
        val bookingEmail = findViewById<EditText>(R.id.booking_email)
        val sendDatabtn = findViewById<Button>(R.id.book_btn)

        // below line is used to get the
        // instance of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase!!.getReference("ParkingInfo")

        // initializing our object
        // class variable.
        parkingInfo = ParkingInfo()


        // adding on click listener for our button.
        sendDatabtn.setOnClickListener { v: View? ->

            // getting text from our edittext fields.
            val number = vehicleNumber.text.toString()
            val mail = bookingEmail.text.toString()

            // below line is for checking whether the
            // edittext fields are empty or not.
            if (TextUtils.isEmpty(number) && TextUtils.isEmpty(mail)) {
                // if the text fields are empty
                // then show the below message.
                Toast.makeText(
                    this@BookingActivity,
                    "Please fill in the fields.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // else call the method to add
                // data to our database.
                addDatatoFirebase(number, mail)
            }
//            supportFragmentManager.beginTransaction().replace(R.id.payment_id, PaymentFragment())
//                .commit()
        }
    }

    private fun addDatatoFirebase(number: String, mail: String) {
        // below lines of code are used to set
        // data in our object class.
        parkingInfo!!.bookingEmail = mail
        parkingInfo!!.carNumber = number

        // we are using add value event listener method
        // which is called with database reference.
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will send data to firebase.
                databaseReference!!.setValue(parkingInfo)

                // after adding this data we are showing a toast message.
                Toast.makeText(this@BookingActivity, "Parking space booked", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onCancelled(error: DatabaseError) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(
                    this@BookingActivity,
                    "Failed to book parking space $error",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }



}