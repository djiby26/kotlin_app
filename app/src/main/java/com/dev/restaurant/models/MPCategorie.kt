package com.dev.restaurant.models

class MPCategorie {

    var id:String? = null
    var name:String? = null
    var image:String? = null

    constructor()
    constructor(id: String?, name: String?, image: String?) {
        this.id = id
        this.name = name
        this.image = image
    }

}