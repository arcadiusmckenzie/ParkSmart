package com.example.parksmart

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val loginadmin = findViewById<TextView>(R.id.admin_login)

        loginadmin.setOnClickListener{
            val intent = Intent(this,AdminActivity::class.java)
            startActivity(intent)
        }

        mAuth= FirebaseAuth.getInstance()



        loginUser()
    }


    private fun loginUser() {
        val loginbutton = findViewById<Button>(R.id.login)

        val email = findViewById<EditText>(R.id.username1)
        val password = findViewById<EditText>(R.id.password1)

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

                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()

                                Toast.makeText(this,"Logged in successfully",Toast.LENGTH_LONG).show()


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
    }





}