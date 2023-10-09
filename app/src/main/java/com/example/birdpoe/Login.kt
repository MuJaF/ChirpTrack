package com.example.birdpoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        val txtEmail = findViewById<EditText>(R.id.et_email)
        val txtPassword = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener { view: View? ->
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please enter both email and password.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                auth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@Login
                    ) { task ->
                        if (task.isSuccessful) {
                            // Login successful, redirect to the homepage
                            val intent = Intent(this@Login, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Login failed, display an error message
                            Toast.makeText(
                                applicationContext,
                                "Authentication failed. Check your email and password.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}
