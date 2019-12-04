package com.dev.restaurant.callback

import com.dev.restaurant.models.Categorie

interface ICategorieCallback {
    fun onCategorieLoadSuccess(categorieList:List<Categorie>)
    fun onCategorieLoadFailed(message:String)
}