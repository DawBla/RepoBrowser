package com.dawidblaszyk.repobrowser

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dawidblaszyk.repobrowser.dto.GithubRepoJSONItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_entry.view.*
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.telecom.Call
import androidx.core.content.ContextCompat


class RecyclerAdapter(
    val githubItems: List<GithubRepoJSONItem>
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var repoName: TextView
        var repoOwner: TextView
        var repoAvatar: ImageView
        var cardView: CardView
        var con: Context

        init {
            repoName = itemView.entry_repo_name
            repoOwner = itemView.entry_repo_owner
            repoAvatar = itemView.entry_repo_avatar
            cardView = itemView.card_view
            con = itemView.context
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_entry, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repoName.text = githubItems[position].name
        holder.repoOwner.text = githubItems[position].owner.login
        Picasso.get()
            .load(githubItems[position].owner.avatar_url)
            .into(holder.repoAvatar)

        holder.cardView.setOnClickListener{
            val intent = Intent(holder.con, DetailsActivity::class.java)
            intent.putExtra("repoName", githubItems[position].name)
            intent.putExtra("repoOwner", githubItems[position].owner.login)
            intent.putExtra("repoAvatar", githubItems[position].owner.avatar_url)
            intent.putExtra("repoDesc", githubItems[position].description)
            holder.con.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return githubItems.size
    }
}