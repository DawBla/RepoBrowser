package com.dawidblaszyk.repobrowser.api

import com.dawidblaszyk.repobrowser.dto.GithubRepoJSONItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("repositories")
    fun getData() : Call<List<GithubRepoJSONItem>>
}