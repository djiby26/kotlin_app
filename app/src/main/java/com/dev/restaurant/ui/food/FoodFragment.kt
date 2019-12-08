package com.dev.restaurant.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.restaurant.R
import com.dev.restaurant.adapter.FoodAdapter
import com.dev.restaurant.common.Common

class FoodFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel
    var recyclerFoodList:RecyclerView? = null
    var layoutAnimationController:LayoutAnimationController? = null
    var adapter:FoodAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodViewModel =
            ViewModelProviders.of(this).get(FoodViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_food, container, false)
        initViews(root)
        foodViewModel.getFoodList().observe(this, Observer {
            adapter = FoodAdapter(context!!,it)
            recyclerFoodList!!.adapter = adapter
            recyclerFoodList!!.layoutAnimation = layoutAnimationController
        })
        return root
    }

    private fun initViews(root: View?) {
        recyclerFoodList = root!!.findViewById(R.id.recycler_food) as RecyclerView
        recyclerFoodList!!.setHasFixedSize(true)
        recyclerFoodList!!.layoutManager = LinearLayoutManager(context)

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_item_from_left)

        (activity as AppCompatActivity).supportActionBar!!.title = Common.CATEGORY_SELECTED!!.name
    }
}