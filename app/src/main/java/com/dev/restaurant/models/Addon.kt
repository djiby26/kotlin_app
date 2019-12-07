package com.dev.restaurant.models

class Addon {

    var name:String? = null
    var price:Long = 0


    constructor()

    constructor(name: String?, price: Long) {
        this.name = name
        this.price = price
    }
}
