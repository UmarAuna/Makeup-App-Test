package com.makeup.makeupapptest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.makeup.makeupapptest.home.models.ProductListItem
import com.makeup.makeupapptest.home.viewmodels.MakeUpListFragmentViewModel
import com.makeup.makeupapptest.koin.apiModule
import com.makeup.makeupapptest.koin.networkModule
import com.makeup.makeupapptest.koin.repositoryModule
import com.makeup.makeupapptest.koin.viewModelModule
import com.makeup.makeupapptest.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MakeUpListFragmentViewModelTest : KoinTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val viewModel: MakeUpListFragmentViewModel by inject()

    @Mock
    private lateinit var makeUpResponseObserver: Observer<Resource<List<ProductListItem>>>

    @Mock private lateinit var context: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(context)
            modules(
                viewModelModule,
                networkModule,
                repositoryModule,
                apiModule
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `when calling for results then return loading`() {
        coroutineRule.runBlockingTest {
            viewModel.observeMakeupList.observeForever(makeUpResponseObserver)
            viewModel.init()
            verify(makeUpResponseObserver).onChanged(Resource.loading(null))
        }
    }
}
