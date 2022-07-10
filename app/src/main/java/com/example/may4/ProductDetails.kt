package com.example.may4

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.may4.productdetails.ProductDetailsViewModel
import com.example.may4.repos.ProductRepository
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductDetails : AppCompatActivity() {
    lateinit var viewModel: ProductDetailsViewModel
    private var available: TextView? = null
    var photo: ImageView? = null
    var thePriceOfProduct: TextView? = null
    private var productName:TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        available = findViewById(R.id.availability)
        productName = findViewById(R.id.product_name)
        photo = findViewById(R.id.photo)
        thePriceOfProduct = findViewById(R.id.thePriceOfProduct)


        val title = intent.getStringExtra("title")

        val products = ProductRepository().getProductByName(title.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it: Products ->
                productName?.text = it.title
                Picasso.get().load(it.photoUrl).into(photo)
                thePriceOfProduct!!.text = "$${it.price}"

            },{

            })

        available!!.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hi, $title is Available!")
                .setPositiveButton("OK"
                ) { p0, p1 ->
                }
                .create()
                .show()
        }


    }

}