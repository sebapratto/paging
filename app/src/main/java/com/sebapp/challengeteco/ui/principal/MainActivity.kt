package com.sebapp.challengeteco.ui.principal

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

    private fun itemDetail() {
        recyclerViewAdapter.setOnItemClickListener {
            Toast.makeText(this, "Item seleccionado: $it", Toast.LENGTH_SHORT).show()
        }
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

    /** Set window style */

    private fun setWindowStyle() {

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