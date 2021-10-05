package com.dawidblaszyk.repobrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawidblaszyk.repobrowser.R.layout.activity_main
import com.dawidblaszyk.repobrowser.api.ApiInterface
import com.dawidblaszyk.repobrowser.dto.GithubRepoJSONItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com/"

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        recycler_repo.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recycler_repo.layoutManager = linearLayoutManager

        getData()
    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<GithubRepoJSONItem>?> {
            override fun onResponse(
                call: Call<List<GithubRepoJSONItem>?>,
                response: Response<List<GithubRepoJSONItem>?>
            ) {
                val responseBody = response.body()!!

                recyclerAdapter = RecyclerAdapter(responseBody)
                recyclerAdapter.notifyDataSetChanged()
                recycler_repo.adapter = recyclerAdapter
            }

            override fun onFailure(call: Call<List<GithubRepoJSONItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })

    }

}