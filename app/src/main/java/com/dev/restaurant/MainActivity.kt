package com.dev.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.restaurant.MenuAdapter.Listener
import com.dev.restaurant.models.Menu
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Listener {


//    lateinit var recyclerView:RecyclerView
    private var recyclerAdapter: MenuAdapter? = null
    private var compositeDisposable:CompositeDisposable? = null
    private var menuList:List<Menu>? = null
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        initRecyclerView()

        loadData()

//        val call: Call<List<Menu>> = apiClient.getMenu()
//
//        call.enqueue(object : Callback<List<Menu>> {
//
//            override fun onResponse(call: Call<List<Menu>>, response: Response<List<Menu>>) {
//                if(response.body() != null){
////                    recyclerAdapter.setMenuListItems(response.body()!!)
//                }
//
//                Log.d("reponse", response.body()?.get(1)?.nom)
////                afficherMenu(response.body())
//
//            }
//            override fun onFailure(call: Call<List<Menu>>, t: Throwable) {
//                Log.d("reponse", t.message)
//            }
//        })
//        recyclerView = recycler_view
//        recyclerAdapter = MenuAdapter(this.)x
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = recyclerAdapter


    }

    private fun initRecyclerView() {
        recycler_view.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
    }

    private fun loadData() {
        val requestInterface: ApiClient = Retrofit.Builder()
            .baseUrl( "http://0ec7cdc8.ngrok.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiClient::class.java)

        compositeDisposable?.add(requestInterface.getMenu()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse,this::handleError)
        )



//        private val apiClient:ApiClient = retrofit.create(ApiClient::class.java)
    }

    private fun handleResponse(menuListe:List<Menu>) {
//        menuList = ArrayList(menuListe)
        recyclerAdapter = MenuAdapter(menuListe, this)
        recycler_view.adapter = recyclerAdapter
    }

    private fun handleError(error:Throwable){
        Log.d(TAG,error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}",
            Toast.LENGTH_SHORT).show()
    }




//    private fun getMenu() {
//
//
////        val menus = List<Menu>().
//    }

    override fun onItemClick(menu: Menu) {
        Toast.makeText(this, "${menu.nom} Clicked !", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

//    private fun afficherMenu(menu: List<Menu>?) {
//
//    }
}
