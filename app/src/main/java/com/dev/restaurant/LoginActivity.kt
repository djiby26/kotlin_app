package com.dev.restaurant

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dev.restaurant.models.User
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl( "http://792f07b4.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create())

    val PREF_FILE = "com.dev.restaurant.prefs"
    var sharedPref : SharedPreferences? = null

    private val retrofit = builder.build()

    private val apiClient:ApiClient = retrofit.create(ApiClient::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp_text.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        sharedPref = this.getSharedPreferences(
            PREF_FILE,Context.MODE_PRIVATE )
        login.setOnClickListener{ login() }
    }

    private var token=""
    private fun login() {

        val call:Call<User> = apiClient.login(username.text.toString(),password.text.toString())

        call.enqueue( object :Callback<User>{

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val editor = sharedPref?.edit()
                    editor?.putString("token",response.body()?.token)
                    editor?.putString("username",response.body()?.username)
                    editor?.apply()

                    val refToken = sharedPref?.getString("token","user_admin")

                    Log.d("reponse",refToken.toString())
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    token = response.body()?.token.toString()
                }else{
                    Toast.makeText(this@LoginActivity,response.message(),Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(p0: Call<User>, p1: Throwable) {
                Log.d("reponse",p1.message.toString())

                Toast.makeText(this@LoginActivity,p1.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}