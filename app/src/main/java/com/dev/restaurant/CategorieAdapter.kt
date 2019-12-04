package com.dev.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.restaurant.models.Categorie
import de.hdodenhof.circleimageview.CircleImageView

class CategorieAdapter(private var context: Context, private var categorie:List<Categorie>) :
 RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.categorie_item,
            parent,false))
    }

    override fun getItemCount(): Int {
        return categorie.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         Glide.with(context).load(categorie.get(position).image).into(holder.categorieImage!!)
        holder.categorieName!!.setText(categorie.get(position).name)
    }

    inner class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        var categorieName : TextView? = null
        var categorieImage:CircleImageView? = null

        init {
            categorieName = itemView.findViewById(R.id.txt_category) as TextView
            categorieImage = itemView.findViewById(R.id.image_category) as CircleImageView
        }
    }

}