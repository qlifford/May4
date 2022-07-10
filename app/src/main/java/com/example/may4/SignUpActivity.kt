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

class SignUpActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    var tvLog: TextView? = null
    var btnReg: Button? = null
    var edtName: EditText? = null
    var edtPwd: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tvLog = findViewById(R.id.tvLog)
        edtPwd = findViewById(R.id.edtPwd)
        edtName = findViewById(R.id.edtName)
        btnReg = findViewById(R.id.btnReg)
        actionBar = supportActionBar!!
        actionBar.title = "Login"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account In...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        tvLog!!.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()

        }
        btnReg!!.setOnClickListener {
            validateData()
        }
    }
    private fun validateData() {
        email = edtName!!.text.toString().trim()
        password = edtPwd!!.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtName!!.error = "Invalid email"

        }else if (TextUtils.isEmpty(password)) {
            edtPwd!!.error = "Enter password"

        }else if (password.length <2){
            edtPwd!!.error = "Enter at least two characters"

        }else{
            firebaseSignUp()
        }
    }
    private fun firebaseSignUp() {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
                //  Toast.makeText(this,"Account created with $email",
                //    Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this,"SignUp failed ! ${e.message}",
                    Toast.LENGTH_SHORT).show()

            }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

}