package com.dev.restaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.restaurant.models.Menu
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var data:ArrayList<Menu>
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    private fun initView() {
//        recyclerView = recycler_view
//        recyclerView.setHasFixedSize(true)
//        val layoutManger: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
//        recyclerView.layoutManager = layoutManger
////        loadJson()
//    }

//    private fun loadJson() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://1a9fb5e8.ngrok.io/")
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
//            .build()
//        val apiMenu = retrofit.create(ApiClient::class.java)
//        val call:Call<JsonResponse> = apiMenu.getMenu()
//        call.enqueue(object : Callback<JsonResponse>{
//            override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
//                if (response.isSuccessful){
//                    val jsonResponse: JsonResponse? = response.body()
//                    data = ArrayList(jsonResponse?.menu)
//                    menuAdapter = MenuAdapter(data)
//                    recyclerView.adapter = menuAdapter
//                    Toast.makeText(applicationContext,data[2].nom,Toast.LENGTH_LONG).show()
//                }else{
//                    Toast.makeText(applicationContext,"il y'a une erreur",Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
//                Log.d("error",t.message)
//            }
//        })
//    }
}