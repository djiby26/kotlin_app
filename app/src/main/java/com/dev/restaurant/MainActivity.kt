package com.dev.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.dev.restaurant.models.Menu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl( "http://ad227c96.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    private val apiClient:ApiClient = retrofit.create(ApiClient::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMenu()

    }

    private fun getMenu() {

        val call: Call<ArrayList<Menu>> = apiClient.getMenu()

        call.enqueue(object : Callback<ArrayList<Menu>> {

            override fun onResponse(call: Call<ArrayList<Menu>>, response: Response<ArrayList<Menu>>) {
                Toast.makeText(this@MainActivity,"Success",Toast.LENGTH_SHORT).show()
                afficherMenu(response.body())

            }
            override fun onFailure(call: Call<ArrayList<Menu>>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
            }
        })
        val menus = ArrayList<Menu>()
    }

    private fun afficherMenu(menu: List<Menu>?) {

    }
}
