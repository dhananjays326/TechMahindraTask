package com.dhananjaysingh.techmtask.network

import com.dhananjaysingh.techmtask.model.Item_ListModel
import com.dhananjaysingh.techmtask.model.Item_ParentModel
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getPost(): Observable<Item_ParentModel>
}