package com.example.newsreader.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.newsreader.R
import com.google.firebase.auth.FirebaseAuth

/**
 * Class that handles the functionality of logging into the app
 * @author 956013
 */

class LoginActivity : AppCompatActivity() {
    private lateinit var mEmail: EditText
    private lateinit var mPassword: EditText
    private lateinit var logInButton: Button
    private lateinit var registerBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mEmail = findViewById(R.id.emailFieldLogin)
        mPassword = findViewById(R.id.passwordFieldLogin)
        logInButton = findViewById(R.id.loginBtn)
        registerBtn = findViewById(R.id.registerBtn)
        progressBar = findViewById(R.id.progressBarLogin)
        fAuth = FirebaseAuth.getInstance()

        /** Checks if a user is currently signed in or not */
        if (fAuth.currentUser != null) {
            fAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            progressBar.visibility = View.INVISIBLE
        }

        /** uses firebase authentication to validate input and let users log in */
        logInButton.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString()
            val password = mPassword.text.toString()

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

            /** checks login credentials with those stored in firebase */
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Logged In.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        /** redirects users to the register activity */
        registerBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}