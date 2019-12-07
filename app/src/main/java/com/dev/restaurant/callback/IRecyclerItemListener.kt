package com.dev.restaurant.callback

import android.view.View

interface IRecyclerItemListener {

     fun onItemClick(view:View,position: Int)
}