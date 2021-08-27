package com.example.marvelproject

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.model.Result
import com.example.marvelproject.repositories.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

@HiltViewModel
class MarvelViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    val loading = mutableStateOf(false)

    val result: MutableState<List<Result>> = mutableStateOf(listOf())

    val numPages = mutableStateOf(1)

    val page = mutableStateOf(1)

    val nameSearch = mutableStateOf("")

    val textSearch = mutableStateOf("")

    val pageNumberList: MutableState<List<Int>> = mutableStateOf(listOf())


    fun onTextSearchChanged(text: String) {
        textSearch.value = text
    }

    fun searchCharacter(
        name: String
    ) {
        viewModelScope.launch {

            val ts = System.currentTimeMillis().toString()
            val md = MessageDigest.getInstance("MD5")
            val input = ts + PRIVATE_KEY + PUBLIC_KEY
            val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)

            val offset = if (page.value == 1) 0 else PAGE_SIZE * page.value


            repository.searchCharacter(
                name = name,
                limit = PAGE_SIZE,
                offset = offset,
                ts = ts,
                apikey = PUBLIC_KEY,
                hash = hash
            ).onEach { dataState ->
                loading.value = dataState.loading

                dataState.data?.let {

                    if (it.data.total > PAGE_SIZE) {
                        val rest = it.data.total % PAGE_SIZE
                        numPages.value = (it.data.total + rest) / PAGE_SIZE
                    }
                    nameSearch.value = textSearch.value
                    result.value = it.data.results

                    pageNumberList.value = (1..numPages.value).toList()


                    Log.d(DEBUG_TAG, "result.value: ${result.value}")
                    Log.d(DEBUG_TAG, "numpages.value: ${numPages.value}")
                    Log.d(DEBUG_TAG, "page.value: ${page.value}")
                    Log.d(DEBUG_TAG, "total: ${it.data.total}")
                    Log.d(DEBUG_TAG, "text: ${textSearch.value}")
                    Log.d(DEBUG_TAG, "list: ${pageNumberList.value}")
                }

                dataState.error?.let {
                    Log.d(DEBUG_TAG, it)

                }
            }.launchIn(viewModelScope)
        }
    }

    fun resetSearchParams() {
        page.value = 1
        pageNumberList.value = listOf()
    }

    fun nextPage() {
        viewModelScope.launch {
            if (page.value < numPages.value) {
                page.value = page.value + 1

                val ts = System.currentTimeMillis().toString()
                val md = MessageDigest.getInstance("MD5")
                val input = ts + PRIVATE_KEY + PUBLIC_KEY
                val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)

                val offset = if (page.value == 1) 0 else PAGE_SIZE * (page.value - 1)

                repository.searchCharacter(
                    name = nameSearch.value,
                    limit = PAGE_SIZE,
                    offset = offset,
                    ts = ts,
                    apikey = PUBLIC_KEY,
                    hash = hash
                ).onEach { dataState ->
                    loading.value = dataState.loading

                    dataState.data?.let {

                        result.value = it.data.results
                        Log.d(DEBUG_TAG, "result.value: ${result.value}")
                        Log.d(DEBUG_TAG, "numpages.value: ${numPages.value}")
                        Log.d(DEBUG_TAG, "page.value: ${page.value}")
                        Log.d(DEBUG_TAG, "total: ${it.data.total}")
                        Log.d(DEBUG_TAG, "text: ${textSearch.value}")
                    }

                    dataState.error?.let {
                        Log.d(DEBUG_TAG, it)

                    }
                }.launchIn(viewModelScope)
            }


        }

    }

    fun previusPage() {
        viewModelScope.launch {
            if (page.value > 1) {
                page.value = page.value - 1

                val ts = System.currentTimeMillis().toString()
                val md = MessageDigest.getInstance("MD5")
                val input = ts + PRIVATE_KEY + PUBLIC_KEY
                val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)

                val offset = if (page.value == 1) 0 else PAGE_SIZE * (page.value - 1)

                repository.searchCharacter(
                    name = nameSearch.value,
                    limit = PAGE_SIZE,
                    offset = offset,
                    ts = ts,
                    apikey = PUBLIC_KEY,
                    hash = hash
                ).onEach { dataState ->
                    loading.value = dataState.loading

                    dataState.data?.let {

                        result.value = it.data.results
                        Log.d(DEBUG_TAG, "result.value: ${result.value}")
                        Log.d(DEBUG_TAG, "numpages.value: ${numPages.value}")
                        Log.d(DEBUG_TAG, "page.value: ${page.value}")
                        Log.d(DEBUG_TAG, "total: ${it.data.total}")
                        Log.d(DEBUG_TAG, "text: ${textSearch.value}")
                    }

                    dataState.error?.let {
                        Log.d(DEBUG_TAG, it)

                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}