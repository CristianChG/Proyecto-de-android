package com.example.myapplication.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de splashscreen.
 * Gestiona el estado de carga y transición a la siguiente pantalla.
 */
class SplashscreenViewModel : ViewModel() {

    /**
     * Indica si la carga ha finalizado.
     */
    val finishedLoading = MutableLiveData<Boolean>()

    /**
     * Inicia el proceso de carga simulado y actualiza el estado cuando termina.
     */
    fun onCreate() {
        viewModelScope.launch {
            delay(2000)
            finishedLoading.postValue(true)
        }
    }
}
