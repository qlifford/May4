package com.example.may4

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.may4.cart.CartActivity
import com.example.may4.repos.ProductRepository
import com.google.android.material.navigation.NavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
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
    var categoriesRecyclerView: RecyclerView? = null
   // var categoriesRecyclerView: RecyclerView? = null
    var btnSearch: Button? = null
    var edtSearch: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = null
        setSupportActionBar(toolbar)
     //   setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView)


        navView!!.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionAdmin -> {
                    val intent = Intent(this, AdminActivity::class.java)
                    startActivity(intent)
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
        val categories = listOf(
            "shoes", "shoes", "shoes", "shoes", "shoes", "shoes",
            "shoes", "shoes", "shoes", "shoes", "shoes", "shoes",
            "shoes", "shoes", "shoes", "shoes", "shoes", "shoes",
        )
        categoriesRecyclerView!!.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this@MainActivity,
                androidx.recyclerview.widget.RecyclerView.HORIZONTAL,
                false)
            adapter = CategoriesAdapter(categories)
        }

    doAsync {
            val json =
                URL("https://gist.githubusercontent.com/qlifford/5a3321770422f849ee6ae9a0b5f63eec/raw/efb6c66e82e91d8af29b52db830289ee2f194ae8/myepl.json").readText()
            uiThread {
                d("gg", "json: $json")
                val products = Gson().fromJson(json, Array<Products>::class.java).toList()
                recyclerView?.apply {
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = ProductsAdapter(products)
                    progressBar?.visibility = View.GONE
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId
            == R.id.actionCart) {
            startActivity(Intent(this, CartActivity::class.java))
            return true
        }
            drawerLayout!!.openDrawer(GravityCompat.START)
            return true
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
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
