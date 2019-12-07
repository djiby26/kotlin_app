package com.dev.restaurant.models

class Categorie {

    var menuId :String? = null
    var name:String? = null
    var image:String? = null
    var foods:List<Menu>? = null

    constructor(menu_id: String?, name: String?, image: String?, foods: List<Menu>?) {
        this.menuId = menu_id
        this.name = name
        this.image = image
        this.foods = foods
    }

    constructor()
}