package com.desafio.android.github.thaisa.babel.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafio.android.github.thaisa.babel.R
import com.desafio.android.github.thaisa.babel.activities.adapter.PullsViewAdapter
import com.desafio.android.github.thaisa.babel.utils.Data
import com.desafio.android.github.thaisa.babel.utils.Status
import com.desafio.android.github.thaisa.babel.viewmodels.PullsViewModel
import com.desafio.android.github.thaisa.domain.entities.Pulls
import kotlinx.android.synthetic.main.activity_pulls_list.*
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullsActivity : AppCompatActivity() {

    private val viewModel by viewModel<PullsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls_list)
        val owner: String = intent.getStringExtra("NAME_OWNER")
        val nameRepo: String = intent.getStringExtra("NAME_REPO")
        toolbarPull.title = nameRepo.plus(" - PR")
        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        viewModel.getPullsRepoGitHub(owner, nameRepo)
        init()

    }

    private fun init() {
        this.setSupportActionBar(toolbarPull)

        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbarPull.setNavigationOnClickListener(View.OnClickListener {
            this.onBackPressed()
        })
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerViewPull.layoutManager = layoutManager
        recyclerViewPull.setHasFixedSize(true)
        recyclerViewPull.addItemDecoration(DividerItemDecoration(this@PullsActivity, LinearLayoutManager.VERTICAL))
    }

    private fun updateUI(pullsData: Data<List<Pulls>>) {
        when (pullsData.responseType) {
            Status.ERROR -> {
                hideProgress()
                textViewDetailsPr.visibility = View.VISIBLE
                pullsData.error?.message?.let { textViewDetailsPr.text = it }
                pullsData.data?.let { recyclerViewPull.adapter = PullsViewAdapter(it) }
            }
            Status.LOADING -> {
                showProgress()
            }
            Status.SUCCESSFUL -> {
                hideProgress()
                pullsData.data?.let {
                    recyclerViewPull.adapter = PullsViewAdapter(it)
                }
            }
        }
    }

    private fun hideProgress() {
        progressPr.visibility = View.GONE
    }

    private fun showProgress() {
        progressPr.visibility = View.VISIBLE
        textViewDetailsPr.visibility = View.GONE
    }
}