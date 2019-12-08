package com.dev.restaurant.ui.categorie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.restaurant.ApiClient
import com.dev.restaurant.JsonResponse
import com.dev.restaurant.callback.ICategorieCallback
import com.dev.restaurant.common.Common
import com.dev.restaurant.models.Categorie
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategorieViewModel : ViewModel(),ICategorieCallback {

    private var categorieListMutable: MutableLiveData<List<Categorie>>? = null
    private var messageError:MutableLiveData<String> = MutableLiveData()
    private var categorieCallbAckListener: ICategorieCallback? = null

    override fun onCategorieLoadSuccess(categorieList: List<Categorie>) {
        categorieListMutable?.value = categorieList
    }

    override fun onCategorieLoadFailed(message: String) {
        messageError.value = message
        Log.d("Error dab",message)
    }

    init {
        categorieCallbAckListener = this
    }

    fun getCategorieList():MutableLiveData<List<Categorie>>{
        if (categorieListMutable == null)
        {
            categorieListMutable = MutableLiveData()
            loadCategory()
        }
        return categorieListMutable!!
    }

    fun getMessageError():MutableLiveData<String>{
        return messageError
    }

    private fun loadCategory() {

        val tempList = ArrayList<Categorie>()
        val categorieRef= FirebaseDatabase
            .getInstance().getReference(Common.CATEGORY_REF)
        categorieRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                categorieCallbAckListener?.onCategorieLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapshot in p0.children){
                    val model = itemSnapshot.getValue<Categorie>(Categorie::class.java)
                    model!!.menuId = itemSnapshot.key
                    tempList.add(model)
                }
                categorieCallbAckListener?.onCategorieLoadSuccess(tempList)
            }
        })
    }
}