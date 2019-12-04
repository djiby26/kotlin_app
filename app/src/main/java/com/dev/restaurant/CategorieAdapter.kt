package com.dev.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.restaurant.models.Categorie
import com.google.android.gms.common.internal.service.Common

class CategorieAdapter (private var context: Context, private var categorieList:List<Categorie>) :
        RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_categorie_item,
                parent,false))
    }

    override fun getItemCount(): Int {
        return categorieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(categorieList.get(position).image).into(holder.categorieImage!!)
        holder.categorieName!!.setText(categorieList.get(position).name)    }

    inner class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        var categorieName : TextView? = null
        var categorieImage: ImageView? = null

        init {
            categorieName = itemView.findViewById(R.id.categorie_text) as TextView
            categorieImage = itemView.findViewById(R.id.categorie_image) as ImageView
        }
    }

}