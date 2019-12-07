package com.dev.restaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.restaurant.R
import com.dev.restaurant.callback.IRecyclerItemListener
import com.dev.restaurant.common.Common
import com.dev.restaurant.eventBus.CategorieClick
import com.dev.restaurant.models.Categorie
import org.greenrobot.eventbus.EventBus

class CategorieAdapter (private var context: Context, private var categorieList:List<Categorie>) :
        RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(
            R.layout.layout_categorie_item,
                parent,false))
    }

    override fun getItemCount(): Int {
        return categorieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(categorieList.get(position).image).into(holder.categorieImage!!)
        holder.categorieName!!.text = categorieList[position].name

        holder.setListener(object : IRecyclerItemListener{
            override fun onItemClick(view: View, position: Int) {
                Common.CATEGORY_SELECTED = categorieList[position]
                EventBus.getDefault().postSticky(CategorieClick(true,categorieList[position]))
            }

        })
    }

    inner class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        override fun onClick(v: View?) {
        listener!!.onItemClick(v!!,adapterPosition)
        }

        internal var listener: IRecyclerItemListener? = null
        var categorieName : TextView? = null
        var categorieImage: ImageView? = null

        fun setListener(listener: IRecyclerItemListener){
            this.listener = listener
        }

        init {
            categorieName = itemView.findViewById(R.id.categorie_text) as TextView
            categorieImage = itemView.findViewById(R.id.categorie_image) as ImageView
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if(categorieList.size == 1)
            Common.DEFAULT_COLUMN_COUNT
        else{
            if (categorieList.size % 2 == 0)
                Common.DEFAULT_COLUMN_COUNT
            else
                if(position > 1 && position == categorieList.size-1 ) Common.FULL_WIDTH_COLUMN
                else Common.DEFAULT_COLUMN_COUNT
        }

    }

}