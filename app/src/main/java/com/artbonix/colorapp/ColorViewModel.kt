package com.artbonix.colorapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ColorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ColorRepository
    val allColors: LiveData<List<ColorEntity>>
    val pendingCount: MutableLiveData<Int> = MutableLiveData(0)

    init {
        val colorDao = ColorDatabase.getDatabase(application).colorDao()
        repository = ColorRepository(colorDao)
        allColors = repository.allColors
    }

    fun addRandomColor() {
        val randomColor = "#${Integer.toHexString((Math.random() * 0xFFFFFF).toInt())}"
        val timestamp = System.currentTimeMillis()
        viewModelScope.launch {
            repository.insert(ColorEntity(color = randomColor, timestamp = timestamp))
            pendingCount.postValue(pendingCount.value?.plus(1))
        }
    }

    fun syncData() {
        viewModelScope.launch {
            repository.syncWithServer()
            pendingCount.postValue(0)
        }
    }
}