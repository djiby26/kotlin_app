package com.dev.restaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.restaurant.R
import com.dev.restaurant.models.MPCategorie
import de.hdodenhof.circleimageview.CircleImageView

class MPCategorieAdapter(private var context: Context, private var MPCategorie:List<MPCategorie>) :
 RecyclerView.Adapter<MPCategorieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(
            R.layout.mp_categorie_item,
            parent,false))
    }

    override fun getItemCount(): Int {
        return MPCategorie.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         Glide.with(context).load(MPCategorie.get(position).image).into(holder.categorieImage!!)
        holder.categorieName!!.text = MPCategorie.get(position).name
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