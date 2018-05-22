package com.example.msidorov.testdatabindings.ui.base

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * @author m.sidorov
 */
abstract class BaseActivityViewModel(val activity: AppCompatActivity) : BaseViewModel() {

    protected var disposables = CompositeDisposable()
    private var started: Boolean = false;

    override fun isActive(): Boolean {
        return started
    }

    @Synchronized
    override fun onStart(){
        super.onStart()
        started = true
    }

    @Synchronized
    override fun onStop(){
        started = false

        disposables.dispose()
        disposables = CompositeDisposable()
        super.onStop()
    }

}