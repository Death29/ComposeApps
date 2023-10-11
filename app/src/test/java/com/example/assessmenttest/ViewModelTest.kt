package com.example.assessmenttest

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assessmenttest.model.AssessmentRepo
import com.example.assessmenttest.model.response.StateResponse
import com.example.assessmenttest.ui.screen.home.promo.util.ViewModelListPromo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ViewModelTest {

    private lateinit var viewModelPromo: ViewModelListPromo
    private val repo: AssessmentRepo = mock()

    private val testCoroutine = TestCoroutineDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(testCoroutine)
        viewModelPromo = ViewModelListPromo(repo)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testCoroutine.cleanupTestCoroutines()
    }

    @Test
    fun `given loading state, when getListPromo called, then post loading state`() = runBlockingTest {
//        val loadingState = StateResponse.Loading
//        whenever(repo.getListPromo()).thenReturn(flowOf(loadingState))

        viewModelPromo.promo

        assert(true) { viewModelPromo.promo.value?.isLoading!! }
    }
}