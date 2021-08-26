package com.example.marvelproject

import android.util.Log
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

    val result : MutableState<List<Result>> = mutableStateOf(listOf())



    fun searchCharacter(
        name: String
    ) {
        viewModelScope.launch {

            val ts = System.currentTimeMillis().toString()
            val md = MessageDigest.getInstance("MD5")
            val input = ts + PRIVATE_KEY + PUBLIC_KEY
            val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16)

            repository.searchCharacter(
                name = name,
                ts = ts,
                apikey = PUBLIC_KEY,
                hash = hash
            ).onEach { dataState ->
                loading.value = dataState.loading

                dataState.data?.let {
                    result.value = it.data.results


                }

                dataState.error?.let {
                    Log.d(DEBUG_TAG, it)

                }
            }.launchIn(viewModelScope)
        }
    }



}