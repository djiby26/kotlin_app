package com.dev.restaurant

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_card.view.*

class MenuAdapter( private val menus:ArrayList<Menu>):
    RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_card,parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = menus[position].
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nom:TextView = itemView.menu_title
//        val description:TextView = itemView.menu_list_detail
//        val image:ImageView = itemView.menu_list_thumbnail
    }
}