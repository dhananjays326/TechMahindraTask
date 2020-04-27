package com.dhananjaysingh.techmtask.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.dhananjaysingh.techmtask.base.BaseViewModel
import com.dhananjaysingh.techmtask.model.Item_ListModel

class ItemListViewModel : BaseViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postDescription = MutableLiveData<String>()
    private val postimageHref = MutableLiveData<String>()

    fun bind(post: Item_ListModel){
        postTitle.value = post.title
        postDescription.value = post.description
        postimageHref.value = post.imageHref
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostDescription(): MutableLiveData<String> {
        return postDescription
    }
    fun getPostImageHref():MutableLiveData<String>{
        return postimageHref
    }


    @BindingAdapter(value =["imageUrl"])
    fun loadImage(imageView: ImageView, imageUrl:String?){

    }
}