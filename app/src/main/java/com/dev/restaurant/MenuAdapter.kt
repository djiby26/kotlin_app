package com.dev.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.dev.restaurant.models.Menu

class MenuAdapter(context: Context,
                  itemList:List<Menu>,
                  isInfinite:Boolean): LoopingPagerAdapter<Menu>(context,itemList,isInfinite) {
    override fun inflateView(viewType: Int, container: ViewGroup?, listPosition: Int): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.layout_restaurant_item,container!!,false)
    }

    override fun bindView(convertView: View?, listPosition: Int, viewType: Int) {
        val imageView = convertView!!.findViewById<ImageView>(R.id.restaurant_image)
        val textView = convertView.findViewById<TextView>(R.id.restaurant_text)
        Glide.with(context).load(itemList[listPosition].image).into(imageView)
        textView.text = itemList[listPosition].name
    }

}