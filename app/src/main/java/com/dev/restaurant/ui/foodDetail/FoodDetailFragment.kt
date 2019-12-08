package com.dev.restaurant.ui.foodDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andremion.counterfab.CounterFab
import com.bumptech.glide.Glide
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton
import com.dev.restaurant.R
import com.dev.restaurant.models.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FoodDetailFragment : Fragment() {

    private lateinit var foodDetailViewModel: FoodDetailViewModel
    private var foodImage:ImageView? = null
    private var buttonCart:CounterFab? = null
    private var buttonRating:FloatingActionButton? = null
    private var foodName:TextView? = null
    private var foodDescription:TextView? = null
    private var buttonNumber:ElegantNumberButton? = null
    private var foodPrice:TextView? = null
    private var ratingBar:RatingBar? = null
    private var buttonComment:Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodDetailViewModel =
            ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_food_detail, container, false)
        initViews(root)
        foodDetailViewModel.getMutableLiveDataFood().observe(this, Observer {
           displayInfo(it)
        })
        return root
    }

    private fun displayInfo(it: Menu?) {
        Glide.with(context).load(it!!.image).into(foodImage)
        foodName!!.text = StringBuilder(it.name!!)
        foodDescription!!.text = StringBuilder(it.description!!)
        foodPrice!!.text = StringBuilder(it.prix.toString())
    }

    private fun initViews(root: View?) {

        buttonCart = root!!.findViewById(R.id.btn_cart) as CounterFab
        foodImage = root.findViewById(R.id.img_food) as ImageView
        buttonRating = root.findViewById(R.id.btn_rating) as FloatingActionButton
        foodName = root.findViewById(R.id.food_name) as TextView
        foodDescription = root.findViewById(R.id.food_description) as TextView
        foodPrice = root.findViewById(R.id.food_price) as TextView
        buttonNumber = root.findViewById(R.id.number_button) as ElegantNumberButton
        ratingBar = root.findViewById(R.id.rating_bar) as RatingBar
        buttonComment = root.findViewById(R.id.btn_show_comment) as Button
    }
}