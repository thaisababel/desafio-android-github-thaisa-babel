package com.desafio.android.github.thaisa.babel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.desafio.android.github.thaisa.babel.di.useCasesPullsModule
import com.desafio.android.github.thaisa.babel.utils.Data
import com.desafio.android.github.thaisa.babel.utils.Status
import com.desafio.android.github.thaisa.babel.viewmodels.PullsViewModel
import com.desafio.android.github.thaisa.domain.entities.Pulls
import com.desafio.android.github.thaisa.domain.usecases.GetPullsUseCase
import com.desafio.android.github.thaisa.domain.utils.Result
import com.google.common.truth.Truth
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PullsViewModelTest : AutoCloseKoinTest() {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var subject: PullsViewModel

    @Mock
    lateinit var pullsValidResult: Result.Success<MutableList<Pulls>>

    @Mock
    lateinit var pullsInvalidResult: Result.Failure

    @Mock
    lateinit var pulls: MutableList<Pulls>

    @Mock
    lateinit var exception: Exception


    private val getPullsUseCase: GetPullsUseCase by inject()


    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        startKoin {
            modules(listOf(useCasesPullsModule))
        }

        declareMock<GetPullsUseCase>()
        MockitoAnnotations.initMocks(this)
        subject = PullsViewModel(getPullsUseCase)
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
    fun getPullsRepoGitHubTestSuccessful() {
        val liveDataUnderTest = subject.mainState.testObserver()
        Mockito.`when`(getPullsUseCase.invoke("spring-projects", "spring-boot"))
            .thenReturn(pullsValidResult)
        Mockito.`when`(pullsValidResult.data).thenReturn(pulls)
        runBlocking {
            subject.getPullsRepoGitHub("spring-projects", "spring-boot").join()
        }
        Truth.assertThat(liveDataUnderTest.observedValues)
            .isEqualTo(listOf(Data(Status.LOADING), Data(Status.SUCCESSFUL, data = pulls)))

    }

    @Test
    fun getPullsRepoGitHubTestError() {
        val liveDataUnderTest = subject.mainState.testObserver()
        Mockito.`when`(getPullsUseCase.invoke("", "")).thenReturn(pullsInvalidResult)
        Mockito.`when`(pullsInvalidResult.exception).thenReturn(exception)

        runBlocking {
            subject.getPullsRepoGitHub("", "").join()
        }

        Truth.assertThat(liveDataUnderTest.observedValues)
            .isEqualTo(
                listOf(
                    Data(Status.LOADING),
                    Data(Status.ERROR, data = null, error = exception)
                )
            )
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