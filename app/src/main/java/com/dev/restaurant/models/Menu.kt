package com.dev.restaurant.models


data class Menu (

    var id: Int ,
    var nom: String ,
    var detail: String,
    var prix: String ,
    var etat: String,
    var typeMenu: String,
    var quantite: Int,
    var image: String
)