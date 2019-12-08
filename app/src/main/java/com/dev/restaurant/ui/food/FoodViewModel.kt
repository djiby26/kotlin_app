package com.dev.restaurant.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.restaurant.common.Common
import com.dev.restaurant.models.Menu

class FoodViewModel : ViewModel() {

    private var mutableFoodList:MutableLiveData<List<Menu>>? = null

    fun getFoodList():MutableLiveData<List<Menu>>{
        if (mutableFoodList == null)
            mutableFoodList = MutableLiveData()
        mutableFoodList!!.value = Common.CATEGORY_SELECTED!!.foods
        return mutableFoodList!!
    }

}