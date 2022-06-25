package com.example.may4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductDetails : AppCompatActivity() {
    var available: TextView? = null

    private var productName:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        productName = findViewById(R.id.product_name)
        available = findViewById(R.id.availability)


        val title = intent.getStringExtra("title")
        productName!!.text = title

        available!!.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setMessage("Hi, $title is Available!")
                .setPositiveButton("OK"
                ) { _, _ ->
                }
                .create()
                .show()
        }


    }

}