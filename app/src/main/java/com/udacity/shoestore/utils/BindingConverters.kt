package com.udacity.shoestore.utils

import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object BindingConverters {
    @InverseMethod("convertStringToLiveData")
    @JvmStatic
    fun convertLiveDataToString(liveData: LiveData<String>): String {
        return liveData.value ?: ""
    }

    @JvmStatic
    fun convertStringToLiveData(value: String): LiveData<String> {
        val liveData = MutableLiveData<String>()
        liveData.value = value
        return liveData
    }
}