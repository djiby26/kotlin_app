package com.dev.restaurant

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev.restaurant.models.Menu
import kotlinx.android.synthetic.main.row_card.view.*

class MenuAdapter( val menus:List<Menu>,val listener: Listener): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    interface Listener{
        fun onItemClick(menu:Menu)
    }

    private val colors : Array<String> = arrayOf(
        "#EF5350", "#EC407A", "#AB47BC", "#7E57C2", "#5C6BC0", "#42A5F5")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_card,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.nom.text = menus[position].nom
        holder.bind(menus[position],listener,colors,position)

    }

//    fun setMenuListItems(menus: List<Menu>){
//        this.menus = menus
//        notifyDataSetChanged()
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            menu: Menu,
            listener: Listener,
            colors: Array<String>,
            position: Int
        ) {
            itemView.menu_title.text = menu.nom
            itemView.setBackgroundColor(Color.parseColor(colors[position % 6]))
            itemView.setOnClickListener{
                listener.onItemClick(menu)
            }
        }

        var nom:TextView = itemView.menu_title
//        val description:TextView = itemView.menu_list_detail
//        val image:ImageView = itemView.menu_list_thumbnail
    }
}