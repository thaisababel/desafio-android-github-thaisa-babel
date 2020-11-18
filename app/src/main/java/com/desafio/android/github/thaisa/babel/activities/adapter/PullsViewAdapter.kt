package com.desafio.android.github.thaisa.babel.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desafio.android.github.thaisa.babel.R
import com.desafio.android.github.thaisa.domain.entities.Pulls
import kotlinx.android.synthetic.main.item_pulls_list.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class PullsViewAdapter(private val pullsList: List<Pulls>) :
    RecyclerView.Adapter<PullsViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pulls_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(pullsList[position])
    }

    override fun getItemCount(): Int {
        return pullsList.size
    }

    class RecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(pulls: Pulls) {
            view.tvTitlePR.text = pulls.titlePr
            view.tvBody.text = pulls.bodyPr
            view.tvUserNamePr.text = pulls.namePr
            val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val date = formatter.parse(pulls.datePr) as Date
            val newFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
            val finalString = newFormat.format(date)
            view.tvDatePr.text = finalString.toString()
        }
    }
}