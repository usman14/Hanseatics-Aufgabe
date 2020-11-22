package com.usman.hanseaticsaufgabe.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.usman.hanseaticsaufgabe.repository.AppRepository

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PicsViewModel::class.java)) {
            return PicsViewModel(app, appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}