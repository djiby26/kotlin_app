package com.dev.restaurant.ui.foodDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.restaurant.common.Common
import com.dev.restaurant.models.Menu

class FoodDetailViewModel : ViewModel() {

    private var mutableLiveDataFood:MutableLiveData<Menu>? = null

    fun getMutableLiveDataFood():MutableLiveData<Menu>{
        if (mutableLiveDataFood == null)
            mutableLiveDataFood = MutableLiveData()
        mutableLiveDataFood!!.value = Common.selectedFood
        return mutableLiveDataFood!!
    }

}