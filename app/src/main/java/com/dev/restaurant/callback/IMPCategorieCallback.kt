package com.dev.restaurant.callback

import com.dev.restaurant.models.MPCategorie

interface IMPCategorieCallback {
    fun onCategorieLoadSuccess(MPCategorieList:List<MPCategorie>)
    fun onCategorieLoadFailed(message:String)
}