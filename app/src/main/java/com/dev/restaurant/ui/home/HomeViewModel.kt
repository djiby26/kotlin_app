package com.dev.restaurant.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.restaurant.callback.ICategorieCallback
import com.dev.restaurant.callback.IMenuCallback
import com.dev.restaurant.models.MPCategorie
import com.dev.restaurant.models.Menu
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel(),ICategorieCallback, IMenuCallback {

    private var MPCategorieListMutable:MutableLiveData<List<MPCategorie>>? = null
    private var menuListMutable:MutableLiveData<List<Menu>>? = null
    private lateinit var messageError: MutableLiveData<String>
    private var iCategorieLoad:ICategorieCallback = this
    private var iMenuLoad:IMenuCallback = this

    override fun onCategorieLoadSuccess(MPCategorieList: List<MPCategorie>) {
        MPCategorieListMutable!!.value = MPCategorieList
    }

    override fun onCategorieLoadFailed(message: String) {
        messageError.value = message
    }

    val menuList:LiveData<List<Menu>>get() {

        if (menuListMutable == null){
            menuListMutable = MutableLiveData()
            messageError = MutableLiveData()
            loadMenuList()
        }
        return menuListMutable!!
    }

    private fun loadMenuList() {

        val tempList = ArrayList<Menu>()
        val menuRef = FirebaseDatabase
            .getInstance().getReference("Category")
        menuRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                iMenuLoad.onMenugorieLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapshot in p0.children){
                    val model = itemSnapshot
                        .getValue<Menu>(Menu::class.java)
                    tempList.add(model!!)
                }
                iMenuLoad.onMenuLoadSuccess(tempList)
            }

        })
    }

    init {
        iCategorieLoad = this
    }

    val MPCategorieList:LiveData<List<MPCategorie>>get() {

        if (MPCategorieListMutable == null){
            MPCategorieListMutable = MutableLiveData()
            messageError = MutableLiveData()
            loadCategoryList()
        }
        return MPCategorieListMutable!!
    }

    private fun loadCategoryList() {
        val tempList = ArrayList<MPCategorie>()
        val categorieRef = FirebaseDatabase
            .getInstance().getReference("MostPopular")
        categorieRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                iCategorieLoad.onCategorieLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapshot in p0.children){
                    val model = itemSnapshot
                        .getValue<MPCategorie>(MPCategorie::class.java)
                    tempList.add(model!!)
                    Log.d("model",model.name)
                }
                iCategorieLoad.onCategorieLoadSuccess(tempList)
            }

        })
    }

    override fun onMenuLoadSuccess(menuList: List<Menu>) {
        menuListMutable!!.value = menuList
    }

    override fun onMenugorieLoadFailed(message: String) {
        messageError.value = message
    }

}