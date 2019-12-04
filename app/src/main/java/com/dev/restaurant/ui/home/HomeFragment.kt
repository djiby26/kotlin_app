package com.dev.restaurant.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.asksira.loopingviewpager.LoopingViewPager
import com.dev.restaurant.CategorieAdapter
import com.dev.restaurant.MenuAdapter
import com.dev.restaurant.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var recyclerView:RecyclerView? = null
    var viewPager:LoopingViewPager? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        initView(root)

        homeViewModel.categorieList.observe(this, Observer {
            val listData = it
            val adapter = CategorieAdapter(context!!,listData)
            restaurant_recycler.adapter = adapter
        })
        homeViewModel.menuList.observe(this, Observer {
            val adapter = MenuAdapter(this.context!!,it,false)
            viewPager!!.adapter = adapter
        })
        return root
    }

    private fun initView(root:View) {
        viewPager = root.findViewById(R.id.viewpager) as LoopingViewPager
        recyclerView = root.findViewById(R.id.restaurant_recycler) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context,
        RecyclerView.HORIZONTAL,false)
    }

    override fun onResume() {
        super.onResume()
        viewPager!!.resumeAutoScroll()
    }

    override fun onPause() {
        viewPager!!.pauseAutoScroll()
        super.onPause()
    }


}