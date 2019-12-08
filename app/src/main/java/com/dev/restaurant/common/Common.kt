package com.dev.restaurant.common

import com.dev.restaurant.models.Categorie
import com.dev.restaurant.models.Menu

object Common {

    var selectedFood: Menu? = null
    var CATEGORY_SELECTED: Categorie? = null
    const val CATEGORY_REF: String = "Category"
    const val FULL_WIDTH_COLUMN: Int= 1
    const val DEFAULT_COLUMN_COUNT: Int=0

}