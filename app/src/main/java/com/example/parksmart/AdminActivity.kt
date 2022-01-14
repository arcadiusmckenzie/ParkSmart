package com.example.parksmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class AdminActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        mAuth= FirebaseAuth.getInstance()


        loginadmin()
    }


    private fun loginadmin() {
        val loginbutton = findViewById<Button>(R.id.login_admin)

        val email = findViewById<EditText>(R.id.admin_email)
        val password = findViewById<EditText>(R.id.admin_password)

        loginbutton.setOnClickListener {


            when {

                TextUtils.isEmpty(email.text.toString()) -> {
                    email.error = ("Please enter Email")
                    return@setOnClickListener

                }
                TextUtils.isEmpty(password.text.toString()) -> {
                    password.error = ("Please enter Password")
                    return@setOnClickListener


                }
                else -> {
                    mAuth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                        .addOnCompleteListener {
                            if (it.isSuccessful) {

                                val intent = Intent(this, AdminPanel::class.java)
                                startActivity(intent)
                                finish()

                                Toast.makeText(this,"Logged in successfully",Toast.LENGTH_LONG).show()


                            } else {
                                Toast.makeText(
                                    this,
                                    "Error message: " + it.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
//                            Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show()

                            }
                        }
                }

            }
        }
    }
}