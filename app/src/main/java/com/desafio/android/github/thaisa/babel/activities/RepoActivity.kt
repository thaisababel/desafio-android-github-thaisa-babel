package com.desafio.android.github.thaisa.babel.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafio.android.github.thaisa.babel.R
import com.desafio.android.github.thaisa.babel.activities.adapter.RepoViewAdapter
import com.desafio.android.github.thaisa.babel.utils.Data
import com.desafio.android.github.thaisa.babel.utils.Status
import com.desafio.android.github.thaisa.babel.viewmodels.RepoViewModel
import com.desafio.android.github.thaisa.domain.entities.Repo
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepoActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepoViewModel>()
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        viewModel.getRepositoriesGitHub(page)
        init()
    }

    private fun init() {
        this.setSupportActionBar(toolbarRepo)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerViewRepo.layoutManager = layoutManager
        recyclerViewRepo.setHasFixedSize(true)

    }

    private fun updateUI(repoData: Data<MutableList<Repo>>) {
        when (repoData.responseType) {
            Status.ERROR -> {
                hideProgress()
                textViewDetails.visibility = View.VISIBLE
                repoData.error?.message?.let { textViewDetails.text = it }
                repoData.data?.let {
                    recyclerViewRepo.adapter = RepoViewAdapter(it)
                }
            }
            Status.LOADING -> {
                showProgress()
            }
            Status.SUCCESSFUL -> {
                hideProgress()
                repoData.data?.let {
                    recyclerViewRepo.adapter = RepoViewAdapter(it)
                }
            }
        }
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        textViewDetails.visibility = View.GONE
    }
}