package com.example.may4

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetails : AppCompatActivity() {
    var available: TextView? = null
    var photo: ImageView? = null

    private var productName:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        productName = findViewById(R.id.product_name)
        available = findViewById(R.id.availability)


        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photoUrl")
        productName!!.text = title
     //   Picasso.get().load(photoUrl).into(photo)


        available!!.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setMessage("Hi, $title is Available!")
                .setPositiveButton("OK"
                ) { p0, p1 ->
                }
                .create()
                .show()
        }


    }

}