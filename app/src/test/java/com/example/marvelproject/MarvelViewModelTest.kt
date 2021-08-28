package com.example.marvelproject

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marvelproject.datasource.FakeMarvelRepository
import com.example.marvelproject.datasource.response
import com.example.marvelproject.model.DataState
import com.example.marvelproject.model.Response
import com.example.marvelproject.model.Result
import com.example.marvelproject.repositories.MarvelRepository
import com.example.marvelproject.ui.MarvelViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.math.BigInteger
import java.security.MessageDigest

val ts = TS
val md = MessageDigest.getInstance("MD5")
val input = ts + PRIVATE_KEY + PUBLIC_KEY
val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)


@ExperimentalCoroutinesApi
class MarvelViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: MarvelViewModel

    private lateinit var repository: MarvelRepository


    val context = Mockito.mock(Context::class.java)

    @Before()
    fun setup(){
        repository = FakeMarvelRepository()
        viewModel = MarvelViewModel(repository)
    }

    @Test
    fun `get List of result from repository`() = runBlocking{

        viewModel.searchCharacter("iron man", context)

        assert(viewModel.result.value.equals(response.data.results))
        assert(viewModel.numPages.value != 1)
    }

    @Test
    fun `get error as result from repository`() = runBlocking{

        viewModel.searchCharacter("man", context)

        assert(viewModel.result.value != response.data.results)
        assert(viewModel.result.value.isEmpty())
        assert(viewModel.numPages.value == 1)
    }

    @Test
    fun `go to next page if exists`() = runBlocking{
        //call first time
        viewModel.searchCharacter("iron man", context)

        assert(viewModel.numPages.value > 1)
        assert(viewModel.page.value == 1)

        //go to second page
        viewModel.nextPage(context)
        assert(viewModel.page.value == 2)

        //stay in second page
        viewModel.nextPage(context)
        assert(viewModel.page.value == 2)
    }

    @Test
    fun `go to previus page if exists`() = runBlocking{
        //call first time
        viewModel.searchCharacter("iron man", context)

        assert(viewModel.numPages.value > 1)
        assert(viewModel.page.value == 1)

        //go to second page
        viewModel.nextPage(context)
        assert(viewModel.page.value == 2)

        //return to first page
        viewModel.previusPage(context)
        assert(viewModel.page.value == 1)

        //stay in first page
        viewModel.previusPage(context)
        assert(viewModel.page.value == 1)
    }


}