package com.desafio.android.github.thaisa.babel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.desafio.android.github.thaisa.babel.utils.Data
import com.desafio.android.github.thaisa.babel.utils.Status
import com.desafio.android.github.thaisa.babel.viewmodels.base.BaseViewModel
import com.desafio.android.github.thaisa.domain.entities.Pulls
import com.desafio.android.github.thaisa.domain.usecases.GetPullsUseCase
import com.desafio.android.github.thaisa.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PullsViewModel(val getPullsUseCase: GetPullsUseCase) : BaseViewModel() {

    private var mutableMainState: MutableLiveData<Data<List<Pulls>>> = MutableLiveData()
    val mainState: LiveData<Data<List<Pulls>>>
        get() {
            return mutableMainState
        }

    fun getPullsRepoGitHub(owner: String, repo: String) = launch {
        mutableMainState.value = Data(responseType = Status.LOADING)
        when (val result = withContext(Dispatchers.IO) { getPullsUseCase(owner, repo) }) {
            is Result.Failure -> {
                mutableMainState.value = Data(responseType = Status.ERROR, error = result.exception)
            }
            is Result.Success -> {
                mutableMainState.value = Data(responseType = Status.SUCCESSFUL, data = result.data)
            }
        }
    }
}