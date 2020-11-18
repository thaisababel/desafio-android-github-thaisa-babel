package com.desafio.android.github.thaisa.babel.activities.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desafio.android.github.thaisa.babel.R
import com.desafio.android.github.thaisa.babel.activities.PullsActivity
import com.desafio.android.github.thaisa.domain.entities.Repo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo_list.view.*


class RepoViewAdapter(private val repoList: MutableList<Repo>) :
    RecyclerView.Adapter<RepoViewAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    class RecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repo: Repo) {
            view.tvNameRepository.text = repo.nameRepo
            view.tvDescription.text = repo.description
            view.tvUserName.text = repo.nameOwner
            view.tvNumberForks.text = repo.forks.toString()
            view.tvNumbersStars.text = repo.numberStars.toString()
            view.tvNumbersStars.text = repo.numberStars.toString()
            Picasso.get().load(repo.photoOwner).into(view.imgUser)
            view.setOnClickListener {
                val intent = Intent(view.context, PullsActivity::class.java)
                intent.putExtra("NAME_OWNER", repo.nameOwner)
                intent.putExtra("NAME_REPO", repo.nameRepo)
                view.context.startActivity(intent)


            }
        }
    }
}