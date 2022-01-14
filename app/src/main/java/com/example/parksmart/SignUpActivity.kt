  package com.example.parksmart

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val signUpButton = findViewById<Button>(R.id.signup)
        val phoneNumberSignUp = findViewById<EditText>(R.id.username)


        phoneNumberSignUp.setMaxLength(10)




        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("Users1")

        signUpButton.setOnClickListener {
            registerUser()
        }


    }



    private fun registerUser() {

        val phoneNumberSignUp = findViewById<EditText>(R.id.username)
        val emailSignUp = findViewById<EditText>(R.id.email)
        val passwordSignUp = findViewById<EditText>(R.id.password)



        phoneNumberSignUp.toString()
        emailSignUp.toString().trim()
        passwordSignUp.text.toString()

        when {
            TextUtils.isEmpty(phoneNumberSignUp.text.toString()) ->
             {
                 phoneNumberSignUp.error = ("Please enter Phone number")

             }
            TextUtils.isEmpty(emailSignUp.text.toString()) -> {
                emailSignUp.error = ("Please enter Email")

            }
            TextUtils.isEmpty(passwordSignUp.text.toString()) -> {
                passwordSignUp.error = ("Please enter Password")

            }
            else -> {
                mAuth.createUserWithEmailAndPassword(
                    emailSignUp.text.toString().trim(),
                    passwordSignUp.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = mAuth.currentUser
                        val currentUserDB = databaseReference?.child((currentUser?.uid!!))

                        currentUserDB?.child("Phonenumber")?.setValue(phoneNumberSignUp.text.toString())
                        currentUserDB?.child("Email")?.setValue(emailSignUp.text.toString())
                        currentUserDB?.child("Password")?.setValue(passwordSignUp.text.toString())

                        Toast.makeText(this,"SignUp successful",Toast.LENGTH_LONG).show()

                        val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                        finish()


                    } else {
                        Toast.makeText(
                            this,
                            "Error message: " + it.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }
        }
    }

    private fun EditText.setMaxLength(maxLength: Int){
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

}


