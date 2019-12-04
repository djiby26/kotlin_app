package com.dev.restaurant.ui.categorie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategorieViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is categorie Fragment"
    }
    val text: LiveData<String> = _text
}