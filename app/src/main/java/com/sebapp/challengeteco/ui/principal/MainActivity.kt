package com.sebapp.challengeteco.ui.principal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebapp.challengeteco.R
import com.sebapp.challengeteco.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        initViewModel()

        setWindowStyle()

        itemDetail()

    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

        }
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[MainActivityViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    private fun itemDetail() {
        recyclerViewAdapter.setOnItemClickListener { character ->
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("NAME",character.name)
                putExtra("STATUS",character.status)
                putExtra("SPECIES",character.species)
                putExtra("GENDER",character.gender)
                putExtra("IMAGE",character.image)
                putExtra("ORIGIN",character.origin.name)
            })
        }
    }

    private fun setWindowStyle() {

        supportActionBar?.hide()

        val window = window
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        if(android.os.Build.VERSION.SDK_INT > 29){
            window.setDecorFitsSystemWindows(false)
        }else{
            window.addFlags(WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW)
        }

    }



}