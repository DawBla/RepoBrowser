package com.dawidblaszyk.repobrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_details)

        val repoName: String = intent.getStringExtra("repoName").toString()
        val repoOwner: String = intent.getStringExtra("repoOwner").toString()
        val repoAvatar: String = intent.getStringExtra("repoAvatar").toString()
        val repoDesc: String = intent.getStringExtra("repoDesc").toString()

        repo_details_name.text = repoName
        repo_details_owner.text = repoOwner
        repo_details_description.text = repoDesc
        Picasso.get()
            .load(repoAvatar)
            .into(repo_details_avatar)

    }
}