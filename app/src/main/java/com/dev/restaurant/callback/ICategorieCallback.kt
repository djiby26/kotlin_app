package com.dev.restaurant.callback

import com.dev.restaurant.models.MPCategorie

interface ICategorieCallback {
    fun onCategorieLoadSuccess(MPCategorieList:List<MPCategorie>)
    fun onCategorieLoadFailed(message:String)
}