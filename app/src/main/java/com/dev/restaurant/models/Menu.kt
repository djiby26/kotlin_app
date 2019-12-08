package com.dev.restaurant.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Menu {


    var id: String? = null
    var name: String? = null
    var image: String? = null
    var description: String? = null
    var prix: Long = 0
    var addon:List<Addon>?=ArrayList()

    constructor()
    constructor(id: String?, name: String?, image: String?, description: String?) {
        this.id = id
        this.name = name
        this.image = image
        this.description = description
    }

    //    var prix: String ,
//    var etat: String,
//    var typeMenu: String,
//    var quantite: Int,



}