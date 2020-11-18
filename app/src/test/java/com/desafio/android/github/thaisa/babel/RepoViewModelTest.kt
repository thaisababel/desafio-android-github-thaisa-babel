package com.desafio.android.github.thaisa.babel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.desafio.android.github.thaisa.babel.di.useCasesRepoModule
import com.desafio.android.github.thaisa.babel.utils.Data
import com.desafio.android.github.thaisa.babel.utils.Status
import com.desafio.android.github.thaisa.babel.viewmodels.RepoViewModel
import com.desafio.android.github.thaisa.domain.entities.Repo
import com.desafio.android.github.thaisa.domain.usecases.GetRepoUseCase
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mock
import java.lang.Exception
import com.desafio.android.github.thaisa.domain.utils.Result
import com.google.common.truth.Truth
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepoViewModelTest  : AutoCloseKoinTest() {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var subject: RepoViewModel
    @Mock
    lateinit var repoValidResult: Result.Success<MutableList<Repo>>
    @Mock
    lateinit var repoInvalidResult: Result.Failure
    @Mock
    lateinit var repo: MutableList<Repo>
    @Mock
    lateinit var exception: Exception


    private val getRepoUseCase: GetRepoUseCase by inject()


    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        startKoin {
            modules(listOf(useCasesRepoModule))
        }

        declareMock<GetRepoUseCase>()
        MockitoAnnotations.initMocks(this)
        subject = RepoViewModel(getRepoUseCase)
    }
    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        stopKoin()
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun getRepositoriesGitHubTestSuccessful() {
        val liveDataUnderTest = subject.mainState.testObserver()
        Mockito.`when`(getRepoUseCase.invoke(1)).thenReturn(repoValidResult)
        Mockito.`when`(repoValidResult.data).thenReturn(repo)
        runBlocking {
            subject.getRepositoriesGitHub(1).join()
        }
        Truth.assertThat(liveDataUnderTest.observedValues)
            .isEqualTo(listOf(Data(Status.LOADING), Data(Status.SUCCESSFUL, data = repo)))

    }

    @Test
    fun getRepositoriesGitHubTestError() {
        val liveDataUnderTest = subject.mainState.testObserver()
        Mockito.`when`(getRepoUseCase.invoke(-1)).thenReturn(repoInvalidResult)
        Mockito.`when`(repoInvalidResult.exception).thenReturn(exception)

        runBlocking {
            subject.getRepositoriesGitHub(-1).join()
        }

        Truth.assertThat(liveDataUnderTest.observedValues)
            .isEqualTo(listOf(Data(Status.LOADING), Data(Status.ERROR, data = null, error = exception)))
    }

    class TestObserver<T> : Observer<T> {
        val observedValues = mutableListOf<T?>()
        override fun onChanged(value: T?) {
            observedValues.add(value)
        }
    }
    private fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }
}