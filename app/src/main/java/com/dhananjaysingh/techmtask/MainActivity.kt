package com.dhananjaysingh.techmtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhananjaysingh.techmtask.databinding.ActivityMainBinding
import com.dhananjaysingh.techmtask.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainActivityViewModel
    private var errorSnackbar: Snackbar? = null
    private var myApplication:MyApplication = MyApplication()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       myApplication.appComponent.inject(this)


        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main)


        binding.postList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.toolBarTitleLiveData.observe(this, Observer {
            binding.toolbar.title =it.toString()
        })

        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        binding.viewmodel = viewModel

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.swipeRefresh()

            binding.swipeRefreshLayout.isRefreshing=false
        }
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
