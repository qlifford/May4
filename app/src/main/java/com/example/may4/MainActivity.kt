package com.example.may4

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var navView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var frameLayout: FrameLayout? = null
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        recyclerView = findViewById(R.id.recyclerView)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
            MainFragment())
            .commit()

        navView!!.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,
                            MainFragment())
                             .commit()

                }
                R.id.message -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MessagesFragment())
                        .commit()
                }
            }
            it.isChecked = true
            drawerLayout!!.closeDrawers()

            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
        val products = arrayListOf<Products>()
        for (i in 0..1000) {
            products.add(Products("Product #$i", "https://picsum.photos/200", price = 1.99))
        }

        recyclerView?.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = ProductsAdapter(products)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout!!.openDrawer(GravityCompat.START)
        return true
    }

}




//logout profile
/*class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var hFragment:Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         actionBar = supportActionBar!!
         actionBar.title = "Back"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val email = firebaseUser.email
            binding.emailTil.text = email

        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }

}*/
