package com.dhananjaysingh.techmtask.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhananjaysingh.techmtask.R
import com.dhananjaysingh.techmtask.adapter.RecyclerAdapter
import com.dhananjaysingh.techmtask.base.BaseViewModel
import com.dhananjaysingh.techmtask.model.Item_ListModel
import com.dhananjaysingh.techmtask.model.Item_ParentModel
import com.dhananjaysingh.techmtask.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val postListAdapter: RecyclerAdapter = RecyclerAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private var mAndroidArrayList: ArrayList<Item_ListModel>? = null

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    var toolBarTitle = MutableLiveData<String>()
    val toolBarTitleLiveData:LiveData<String>
        get()=toolBarTitle

    init{
        loadPosts()
        toolBarTitle.value = "Tech Mahindra Task"
    }

    private fun loadPosts(){

        subscription = postApi.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                // Add result
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )

    }
    private fun onRetrievePostListStart(){

        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList:Item_ParentModel){

        toolBarTitle.value = postList.title
        mAndroidArrayList = ArrayList(postList.rows)

        postListAdapter.RecyclerAdapter(mAndroidArrayList!!)
        postListAdapter.notifyDataSetChanged()

    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


    fun swipeRefresh(){
        loadPosts()
    }


}