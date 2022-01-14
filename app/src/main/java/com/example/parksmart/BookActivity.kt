package com.example.parksmart

//import android.R


import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*


class BookActivity : AppCompatActivity() {

    var t1Hour = 0
    var t1minute:Int = 0
    var t2Hour:Int = 0
    var t2minute:Int = 0
    var timer1: TextView? = null
    var timer2: TextView? = null



//    var timer1 = findViewById<TextView>(R.id.starttime)
//    var timer2 = findViewById<TextView>(R.id.endtime)






    // creating a variable for our
    // Firebase Database.
    var firebaseDatabase: FirebaseDatabase? = null

    // creating a variable for our Database
    // Reference for Firebase.
    var databaseReference: DatabaseReference? = null

    // creating a variable for
    // our object class
    var bookingInfo: BookingInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)




        timer1?.setOnClickListener {
            StartTime()
        }

        timer2?.setOnClickListener {
            EndTime()
        }

        // initializing our edittext and button
        var email = findViewById<EditText>(R.id.booking_email)
        var carNumber = findViewById<EditText>(R.id.vehicle_id)
        var time = findViewById<Chronometer>(R.id.simpleChronometer)

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase!!.getReference("BookingInfo")

        // initializing our object
        // class variable.
        bookingInfo = BookingInfo()
        var sendDatabtn = findViewById<Button>(R.id.book_btn)

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener {
            // getting text from our edittext fields.
            val mail = email.text.toString()
            val number = carNumber.text.toString()
            val timer = time.text.toString()

            // below line is for checking weather the
            // edittext fields are empty or not.
            if (TextUtils.isEmpty(mail) && TextUtils.isEmpty(number)) {
                // if the text fields are empty
                // then show the below message.
                Toast.makeText(this@BookActivity, "Please fill in the fields.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // else call the method to add
                // data to our database.
                addDataToFirebase(mail, number, timer)
            }


            val simpleChronometer =
                findViewById<View>(R.id.simpleChronometer) as Chronometer // initiate a chronometer


            simpleChronometer.start() // start a chronometer


            val intent = Intent(this,PaymentActivity::class.java)
            startActivity(intent)
            
        }
    }

    private fun addDataToFirebase(mail: String, number: String, timer: String) {
        // below 3 lines of code is used to set
        // data in our object class.
        bookingInfo!!.email = mail
        bookingInfo!!.carNumber = number
        bookingInfo!!.time = timer

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference!!.setValue(bookingInfo)

                // after adding this data we are showing toast message.
                Toast.makeText(this@BookActivity, "Parking space booked", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(
                    this@BookActivity,
                    "Failed to book parking space $error",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun EndTime() {
        val timePickerDialog = TimePickerDialog(this,
            { view, hourOfDay, minute ->
                t2Hour = hourOfDay
                t2minute = minute
                val calendar: Calendar = Calendar.getInstance()
                calendar.set(0, 0, 0, t2Hour, t2minute)
                timer2!!.text = android.text.format.DateFormat.format("hh:mm:aa", calendar)
            }, 12, 0, false
        )
        timePickerDialog.updateTime(t2Hour, t2minute)
        timePickerDialog.show()
    }

    fun StartTime() {
        val timePickerDialog = TimePickerDialog(this,
            { view, hourOfDay, minute ->
                t1Hour = hourOfDay
                t1minute = minute
                val calendar: Calendar = Calendar.getInstance()
                calendar.set(0, 0, 0, t1Hour, t1minute)
                timer1!!.text = android.text.format.DateFormat.format("hh:mm:aa", calendar)
            }, 12, 0, false
        )
        timePickerDialog.updateTime(t1Hour, t1minute)
        timePickerDialog.show()
    }


}