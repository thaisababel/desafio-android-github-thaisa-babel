package com.desafio.android.github.thaisa.babel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.desafio.android.github.thaisa.babel.utils.Data
import com.desafio.android.github.thaisa.babel.utils.Status
import com.desafio.android.github.thaisa.babel.viewmodels.base.BaseViewModel
import com.desafio.android.github.thaisa.domain.entities.Repo
import com.desafio.android.github.thaisa.domain.usecases.GetRepoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import com.desafio.android.github.thaisa.domain.utils.Result

class RepoViewModel (val getRepoUseCase: GetRepoUseCase) : BaseViewModel() {

    private var mutableMainState: MutableLiveData<Data<MutableList<Repo>>> = MutableLiveData()
    val mainState: MutableLiveData<Data<MutableList<Repo>>>
        get() {
            return mutableMainState
        }

    fun getRepositoriesGitHub(page: Int) = launch {
        mutableMainState.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getRepoUseCase(page) }) {
            is Result.Failure -> {
                mutableMainState.value = Data(responseType = Status.ERROR, error = result.exception)
            }
            is Result.Success -> {
                mutableMainState.value = Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }
}