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

class MainActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var navView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var frameLayout: FrameLayout? = null
    var progressBar: ProgressBar? = null
    var categoriesRecyclerView: RecyclerView? = null
    var btnSearch: Button? = null
    var edtSearch: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = null
        // setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        btnSearch = findViewById(R.id.btnSearch)
        edtSearch = findViewById(R.id.edtSearch)
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
                false
            )
            adapter = CategoriesAdapter(categories)
        }

        val productRepository = ProductRepository().getAllProducts()
        loadRecyclerView(productRepository)

        btnSearch!!.setOnClickListener {
            loadRecyclerView(ProductRepository().searchForProducts(edtSearch!!.text.toString()))
        }
    }

    fun loadRecyclerView(productRepository: Single<List<Products>>) {
        val single = productRepository
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                d("v", "success :)")
                recyclerView?.apply {
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = ProductsAdapter(it)
                }
                progressBar?.visibility = View.GONE

            }, {
                d("v", "error :(${it.message}")
            })
        //  btnSearch = findViewById(R.id.btnSearch)

        /* btnSearch!!.setOnClickListener {
            doAsync {
                //  val json =
                //   URL("https://gist.githubusercontent.com/qlifford/5a3321770422f849ee6ae9a0b5f63eec/raw/efb6c66e82e91d8af29b52db830289ee2f194ae8/myepl.json").readText()
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()
                val productsFromDatabase = db.productDao().searchFor("%${edtSearch!!.text}%")
                val products = productsFromDatabase.map {
                    Products(
                        it.title,
                        "https://m.media-amazon.com/images/I/71mrVC9SyhL._AC_UL640_QL65_.jpg",
                        222.12,
                        true)
                }
                uiThread {
                    //   d("gg", "json: $json")
                    //   val products = Gson().fromJson(json, Array<Products>::class.java).toList()
                    recyclerView?.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = ProductsAdapter(products)
                    }
                        progressBar?.visibility = View.GONE
                    }
                }
            }*/
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