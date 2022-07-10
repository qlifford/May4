package com.example.may4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {
    private var btnSubmit: Button? = null
    private var edtTitle: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        btnSubmit = findViewById(R.id.btnSubmit)
        edtTitle = findViewById(R.id.edtTitle)

        btnSubmit!!.setOnClickListener {
          //  val id = "Products"
           // var ref = FirebaseDatabase.getInstance().getReference(ProductRepository()).child("Users/$id")


            }
    }
}



            /*   val title = edtTitle?.text
            d("cliff", "button pressed :) with text of $edtTitle")

            doAsync {
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()
                db.productDao().insertAll(ProductFromDatabase(null,title.toString(),232.12,true))
                uiThread {
                       d("gg", "Home screen")

                }
            }
        }
    }*/
