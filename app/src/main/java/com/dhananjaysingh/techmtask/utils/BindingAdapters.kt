package com.dhananjaysingh.techmtask.utils

import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dhananjaysingh.techmtask.R
import com.squareup.picasso.Picasso

@BindingAdapter("mutableVisibility")
fun setMutability(view : View, visibility: MutableLiveData<Int>?){
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUrl","error")
fun setImageView(imageView: ImageView,url:String?, error:Drawable){

    if(!url.equals("")){
        var urls: String="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            val newUrl = url?.replace("http","https")
            Picasso.get().load(newUrl).resize(200,200).placeholder(R.mipmap.ic_image_not_found).error(error).into(imageView)

    }
}

