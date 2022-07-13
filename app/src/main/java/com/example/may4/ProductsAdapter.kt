@file:Suppress("DEPRECATION")

package com.example.may4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductsAdapter(private val products: List<Products>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString()
        //holder.price.text = "is it on sale? ${products.isOnSale}"
         // if (product.isOnSale){
           // holder.price.text = "ON SALE"
       //  }else{
       //      holder.price.text = "Not On  Sale"
        //  }
      // if (product.isOnSale){
       //    holder.saleImageView.visibility = View.GONE
      // }else {
      //     holder.saleImageView.visibility = View.VISIBLE
      //  }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row,parent,false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context,ProductDetails::class.java)
            intent.putExtra("title",products[holder.adapterPosition].title)
            intent.putExtra("photoUrl",products[holder.adapterPosition].photoUrl)
            parent.context.startActivity((intent))
        }
        return holder
    }
    override fun getItemCount() = products.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
    //    val saleImageView: ImageView = itemView.findViewById(R.id.saleImageView)

    }

}
