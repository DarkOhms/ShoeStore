package com.udacity.shoestore

import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.databinding.Bindable
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.NewShoe
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class StoreViewModel: ViewModel() {

    /*
    Login and Password fields
     */
    private val _loginEmail = MutableLiveData<String>()

    val loginEmail:LiveData<String>
        get() = _loginEmail

    private val _loginPassword = MutableLiveData<String>()

    val loginPassword:LiveData<String>
        get() = _loginPassword

    //Shoe List LiveData
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    lateinit var newShoe:Shoe

    //navigation bool
    private val _navigate = MutableLiveData<Boolean>()

    val navigate: LiveData<Boolean>
        get() = _navigate
    //input error bool
    private val _inputError = MutableLiveData<Boolean>()

    val inputError: LiveData<Boolean>
        get() = _inputError

    /*
    New shoe variables for data binding
     */

    var newShoeData = NewShoe("","","","")
    private val _newShoeName = MutableLiveData<String>()

    /* I figured LiveData was the way to go but maybe not???
    val newShoeName: LiveData<String>
        get() = _newShoeName

    private val _newShoeCompany = MutableLiveData<String>()

    val newShoeCompany: LiveData<String>
        get() = _newShoeCompany

    private val _newShoeSize = MutableLiveData<Double>()

    val newShoeSize: LiveData<Double>
        get() = _newShoeSize

    private val _newShoeDescription = MutableLiveData<String>()

    val newShoeDescription: LiveData<String>
        get() = _newShoeDescription

     */

    //end new shoe variables

    init {
        _navigate.value = false
        _inputError.value = false
        _shoeList.value = mutableListOf<Shoe>()
    }

    //Simple navigation. We can complicate further if there are more destinations
    fun navigate(){
        _navigate.value = true
    }

    fun onNavigationComplete(){
        _navigate.value = false
    }

    //details functions
    fun onSave(){
        Timber.i("onSaved clicked")
        val badInput = checkForErrorCondition()
        Timber.i("onSaved clicked bad input = " + badInput.toString())
        if(badInput) {
            //TODO handle error
        }else{
            makeNewShoe()
            storeNewShoe()
            navigate()
        }
    }

    fun makeNewShoe(){

            newShoe = Shoe(
                newShoeData.name,
                //this may need some tidying up
                newShoeData.size.toDouble(),
                newShoeData.company,
                newShoeData.description
            )
    }

    private fun checkForErrorCondition(): Boolean{
        Timber.i("newShoeName = " + newShoeData.name)
        val error: Boolean = (newShoeData.name == null
                || newShoeData.size == null
                || newShoeData.company == null
                || newShoeData.company == null)

        _inputError.value =error
        return error
    }
    private fun storeNewShoe(){

        _shoeList.value?.add(newShoe)
    }

}