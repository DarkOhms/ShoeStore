package com.udacity.shoestore.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:text")
    fun setDoubleText(view: EditText, value: String) {
        if (view.text.toString() != value) {
            view.setText(value)
        }
    }

    @JvmStatic
    @BindingAdapter("android:textAttrChanged")
    fun setListener(view: EditText, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                listener.onChange()
            }
        })
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "android:text")
    fun getText(view: EditText): String {
        return view.text.toString()
    }
}
