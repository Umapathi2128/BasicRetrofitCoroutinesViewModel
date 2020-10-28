package com.example.basicretrofitcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicretrofitcoroutines.R
import com.example.basicretrofitcoroutines.data.api.ApiHelper
import com.example.basicretrofitcoroutines.data.api.RetrofitBulder
import com.example.basicretrofitcoroutines.data.model.User
import com.example.basicretrofitcoroutines.utils.Status
import com.example.basicretrofitcoroutines.utils.Status.*
import com.gm.basic.ui.main.TopDissolveLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MainAdapter(arrayListOf())
        setViewModel()
        setUpUI()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                SUCCESS -> {
                    progressbar.visibility = View.GONE
                    reclerview.visibility = View.VISIBLE
                    it.data?.let { it1 -> retriveUsersList(it1) }
                }
                LOADING -> {
                    progressbar.visibility = View.VISIBLE
                    reclerview.visibility = View.GONE
                }
                ERROR -> {
                    progressbar.visibility = View.GONE
                    reclerview.visibility = View.VISIBLE
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        })
    }

    private fun setUpUI() {

//        reclerview.layoutManager = LinearLayoutManager(this)
        reclerview.layoutManager = TopDissolveLayoutManager()
//        reclerview.itemAnimator = DefaultItemAnimator()
//        reclerview.addItemDecoration(DividerI)
        reclerview.adapter = adapter
//        reclerview.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
////            addItemDecoration(
////                DividerItemDecoration(
////                    reclerview.context,
////                    (reclerview.layoutManager as LinearLayoutManager).orientation
////                )
////            )
//            this.adapter = adapter
//        }
    }

    private fun setViewModel() {
        viewModel =
            ViewModelProviders.of(this, MainViewModelFactory(ApiHelper(RetrofitBulder.apiService)))
                .get(MainViewModel::class.java)
    }

    private fun retriveUsersList(user: List<User>) {
        reclerview.visibility = View.VISIBLE
        adapter.apply {
            addUsers(user)
            notifyDataSetChanged()
        }
    }
}