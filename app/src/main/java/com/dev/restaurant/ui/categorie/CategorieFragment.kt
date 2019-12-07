package com.dev.restaurant.ui.categorie

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.restaurant.adapter.CategorieAdapter
import com.dev.restaurant.R
import com.dev.restaurant.common.SpaceItemDecoration
import com.dev.restaurant.common.common
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_category.*

class CategorieFragment : Fragment() {

    private lateinit var categorieViewModel: CategorieViewModel
    private lateinit var dialog:AlertDialog
    private lateinit var layoutAnimationController: LayoutAnimationController
    private var adapter: CategorieAdapter? = null
    var recyclerCategorie:RecyclerView?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categorieViewModel =
            ViewModelProviders.of(this).get(CategorieViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_category, container, false)

        initViews(root)

        categorieViewModel.getMessageError().observe(this, Observer{
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        })
        categorieViewModel.getCategorieList().observe(this, Observer {
            dialog.dismiss()
            adapter = CategorieAdapter(context!!,it)
            recyclerCategorie!!.adapter = adapter
            recyclerCategorie!!.layoutAnimation = layoutAnimationController
        })

        return root
    }

    private fun initViews(root:View) {
        dialog = SpotsDialog.Builder().setContext(context)
            .setCancelable(false).build()
        dialog.show()
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,
            R.anim.layout_item_from_left)
        recyclerCategorie = root.findViewById(R.id.recycler_categorie) as RecyclerView
        recyclerCategorie!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context,2)
        layoutManager.orientation = RecyclerView.VERTICAL
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if (adapter != null){
                    when(adapter!!.getItemViewType(position)){
                        common.DEFAULT_COLUMN_COUNT -> 1
                        common.FULL_WIDTH_COLUMN -> 2
                        else -> 1
                    }
                }else -1
            }

        }
        recyclerCategorie!!.layoutManager = layoutManager
        recyclerCategorie!!.addItemDecoration(SpaceItemDecoration(8))
    }
}