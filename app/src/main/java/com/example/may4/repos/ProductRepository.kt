package com.example.may4.repos

import com.example.may4.Products
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single
import java.net.URL

class ProductRepository {
    fun getAllProducts(): Single<List<Products>> {
     return  Single.create<List<Products>>() {
         it.onSuccess(fetchProducts())
        }
    }
    fun searchForProducts(term: String): Single<List<Products>> {
        return Single.create<List<Products>> {
            val filteredProducts = fetchProducts().filter { it.title.contains(term, true) }
            it.onSuccess(filteredProducts)
        }
    }

        fun getProductByName(title: String) :Single<Products>{
            return Single.create<Products> {
                val products = fetchProducts().first { it.title == title }
                it.onSuccess(products)
            }

    }
    private fun fetchProducts() : List<Products>{
        val json =  URL("https://gist.githubusercontent.com/" +
                "qlifford/5a3321770422f849ee6ae9a0b5f63eec" +
                "/raw/efb6c66e82e91d8af29b52db830289ee2f194ae8/myepl.json").readText()
       return Gson().fromJson(json, Array<Products>::class.java).toList()
    }

    }
