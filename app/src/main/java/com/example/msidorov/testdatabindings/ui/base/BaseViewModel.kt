package com.example.msidorov.testdatabindings.ui.base

import android.databinding.BaseObservable

/**
 * @author m.sidorov
 */
abstract class BaseViewModel : BaseObservable() {

    open fun isActive(): Boolean{
        return true
    }

    open fun onStart(){}

    open fun onStop(){}

}

