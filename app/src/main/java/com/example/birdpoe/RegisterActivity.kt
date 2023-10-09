package com.example.birdpoe

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        val db = Firebase.firestore

        val txtEmail = findViewById<EditText>(R.id.et_email)
        val txtPassword = findViewById<EditText>(R.id.et_password)
       // val txtConfirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val btnRegister = findViewById<Button>(R.id.btn_register)
        //val tvLoginNow = findViewById<TextView>(R.id.btn_login)

        //Getting all inputs
        btnRegister.setOnClickListener{
            val email: String = txtEmail.text.toString()
            val password: String = txtPassword.text.toString()

            //Ensuring correct inputs
            if (email == null || password == null){
                val toast = Toast.makeText(applicationContext, "Please enter all required fields.", Toast.LENGTH_LONG)
                toast.show()
            }

            else if(password.length < 8){
                val toast = Toast.makeText(applicationContext, "Please ensure password length is greater than 8 characters.", Toast.LENGTH_LONG)
                toast.show();
            }
            else{
                //Creating new user

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext,"Registration complete.",Toast.LENGTH_SHORT).show()

                            /*----------------------------------------------------*/
                            /*Getting UID of current user and creating new doc in "users" collection*/
                            val uid = FirebaseAuth.getInstance().currentUser?.uid
                            print("uid")
                            val user = hashMapOf(
                                "username" to "${email}"
                            )
                            val userDocumentRef = db.collection("users").document(uid.toString())

                            val intent = Intent(this, MainActivity::class.java)
                            // start your next activity
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(applicationContext,"Authentication failed.",Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

    }

}