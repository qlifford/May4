package com.example.may4

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var tvMember : TextView? = null
    private var edtPwd : EditText? = null
    private var btnLogin : Button? = null
    private var edtName : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        tvMember = findViewById(R.id.tvMember)
        edtName = findViewById(R.id.edtName)
        edtPwd = findViewById(R.id.edtPwd)
        btnLogin = findViewById(R.id.btnLogin)
        actionBar = supportActionBar!!
        actionBar.title = "Login"
        actionBar.setDisplayShowHomeEnabled(true)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        tvMember!!.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnLogin!!.setOnClickListener {

            validateData()
        }

    }
    private fun validateData() {
        email = edtName!!.text.toString().trim()
        password = edtPwd!!.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtName!!.error = "Invalid email"

        }else if (TextUtils.isEmpty(password)) {
            edtPwd!!.error = "Enter password"
        }else{
            firebaseLogin()
        }
    }
    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "logged in as $email",
                    Toast.LENGTH_SHORT).show()

                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "login failed! ${e.message}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
    }
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }

}
