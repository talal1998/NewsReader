package com.example.newsreader.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsreader.R
import com.example.newsreader.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Class that handles the functionality of creating user accounts
 * @author 956013
 */

class RegisterActivity : AppCompatActivity() {
    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var mSources: EditText
    private lateinit var signUpButton: Button
    private lateinit var existingUserButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth: FirebaseAuth
    private lateinit var fDB: FirebaseDatabase
    private lateinit var fStore: FirebaseFirestore
    private lateinit var userID: String

    companion object {
        val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mEmail = findViewById(R.id.emailFieldRegister)
        mPassword = findViewById(R.id.passwordFieldRegister)
        mSources = findViewById(R.id.sourceFieldRegister)
        signUpButton = findViewById(R.id.signUpBtn)
        existingUserButton = findViewById(R.id.existingUserButton)
        progressBar = findViewById(R.id.progressBarRegister)
        fAuth = FirebaseAuth.getInstance()
        fDB = FirebaseDatabase.getInstance()
        fStore = FirebaseFirestore.getInstance()

        /** Checks if a user is currently signed in */
        if (fAuth.currentUser != null) {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
            finish()
        }

        /** uses firebase authentication to add user to a realtime database */
        signUpButton.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString()
            val password = mPassword.text.toString()
            val sources = mSources.text.toString()

            if (TextUtils.isEmpty(email)) {
                mEmail.error = "Email Required"
                return@OnClickListener
            }

            if (TextUtils.isEmpty(password)) {
                mPassword.error = "Password Required"
                return@OnClickListener
            }

            if (password.length < 6) {
                mPassword.error = "Password must be greater than 6 characters."
                return@OnClickListener
            }
            progressBar.visibility = View.VISIBLE


            /** stores credentials inside firebase database */
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                    userID = fAuth.currentUser?.uid.toString()
                    val reference = fDB.getReference("/user/$userID")
                    val user: User = User(email, password, sources)

                    reference.setValue(user)
                        .addOnSuccessListener {
                            Log.d(TAG, "User saved to database.")
                        }
                        .addOnFailureListener {
                            Log.d(TAG, "Failed to store values.: ${it.message}")
                        }
                } else {
                    Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        /** redirects users to login activity */
        existingUserButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })

    }

}