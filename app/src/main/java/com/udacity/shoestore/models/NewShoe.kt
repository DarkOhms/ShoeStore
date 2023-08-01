package com.udacity.shoestore.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class NewShoe(var name: String, var size: String, var company: String, var description: String) : BaseObservable() {

    /*
    @Bindable
    fun getName():String{
        return name
    }
    fun setName(name: String) {
        if (this.name != name) {
            this.name = name
        }
    }
    @Bindable
    fun getSize():String{
        return size
    }
    fun setSize(size: String) {
        if (this.size != size) {
            this.size = size
        }
    }
    @Bindable
    fun getCompany():String{
        return company
    }
    fun setCompany(company: String) {
        if (this.company != company) {
            this.company = company
        }
    }
    @Bindable
    fun getDescription():String{
        return description
    }
    fun setDescription(company: String){
        if(this.description != description){
            this.description = description
        }
    }

     */

}