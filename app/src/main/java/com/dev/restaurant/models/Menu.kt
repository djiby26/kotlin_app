package com.dev.restaurant.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Menu {


    var id: Int? = null
    var name: String? = null
    var image: String? = null
    var detail: String? = null

    constructor()
    constructor(id: Int?, name: String?, image: String?, detail: String?) {
        this.id = id
        this.name = name
        this.image = image
        this.detail = detail
    }

    //    var prix: String ,
//    var etat: String,
//    var typeMenu: String,
//    var quantite: Int,



}