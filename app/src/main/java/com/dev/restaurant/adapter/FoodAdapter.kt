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
import com.dev.restaurant.eventBus.FoodItemClick
import com.dev.restaurant.models.Menu
import org.greenrobot.eventbus.EventBus

class FoodAdapter (private var context: Context, private var foodList:List<Menu>) :
    RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_food_item,
                parent,false))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(foodList[position].image).into(holder.foodImage!!)
        holder.foodName!!.text = foodList[position].name
        holder.foodPrice!!.text = foodList[position].prix.toString()

        //Event
        holder.setListener(object : IRecyclerItemListener{
            override fun onItemClick(view: View, position: Int) {
                Common.selectedFood = foodList[position]
                EventBus.getDefault().postSticky(FoodItemClick(true,foodList[position]))
            }

        })

    }

    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(v: View?) {
            listener!!.onItemClick(v!!,adapterPosition)
        }

        var foodName : TextView? = null
        var foodPrice : TextView? = null
        var foodfav : ImageView? = null
        var foodCart : ImageView? = null
        var foodImage: ImageView? = null
        private var listener: IRecyclerItemListener? = null

        fun setListener(listener: IRecyclerItemListener){
            this.listener = listener
        }

        init {
            foodPrice = itemView.findViewById(R.id.txt_food_price) as TextView
            foodName = itemView.findViewById(R.id.txt_food_name) as TextView
            foodImage = itemView.findViewById(R.id.food_image) as ImageView
            foodfav = itemView.findViewById(R.id.image_fav) as ImageView
            foodCart = itemView.findViewById(R.id.image_quick_cart) as ImageView
            itemView.setOnClickListener(this)
        }
    }
}