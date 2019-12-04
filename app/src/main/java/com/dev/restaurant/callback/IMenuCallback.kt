package com.dev.restaurant.callback

import com.dev.restaurant.models.Categorie
import com.dev.restaurant.models.Menu

interface IMenuCallback {

    fun onMenuLoadSuccess(menuList:List<Menu>)
    fun onMenugorieLoadFailed(message:String)
}