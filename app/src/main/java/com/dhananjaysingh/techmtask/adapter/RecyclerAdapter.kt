package com.dhananjaysingh.techmtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhananjaysingh.techmtask.R
import com.dhananjaysingh.techmtask.databinding.SingleRowBinding
import com.dhananjaysingh.techmtask.model.Item_ListModel
import com.dhananjaysingh.techmtask.viewmodel.ItemListViewModel

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var items:List<Item_ListModel>

    fun RecyclerAdapter(items: List<Item_ListModel>){
        this.items = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : SingleRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.single_row,parent,false)

        return ViewHolder(binding);

    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bind(items[position])
    }

    class ViewHolder(private val binding: SingleRowBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = ItemListViewModel()

        fun bind(post:Item_ListModel){
            viewModel.bind(post)
            binding.itemModel = viewModel
        }
    }
}