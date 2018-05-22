package com.example.msidorov.testdatabindings.ui.activity.main

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.msidorov.testdatabindings.R
import com.example.msidorov.testdatabindings.events.ErrorEvent
import com.example.msidorov.testdatabindings.events.OpenFragmentEvent
import com.example.msidorov.testdatabindings.ui.base.BaseActivityViewModel
import com.example.msidorov.testdatabindings.ui.fragments.factory.FragmentsFactory
import com.example.msidorov.testdatabindings.ui.navigation.OpenFragmentData
import com.example.msidorov.testdatabindings.utils.UserException

/**
 * @author m.sidorov
 */
class VM_MainActivity(activity: AppCompatActivity) : BaseActivityViewModel(activity){

    @Synchronized
    override fun onStart(){
        synchronized(this) {
            disposables.add(OpenFragmentEvent.source.subscribe(this::openFragment, this::showError))
            disposables.add(ErrorEvent.source.subscribe(this::showError, this::showError))
        }
        super.onStart()
    }

    fun showError(error: Throwable){
        Log.e("ERROR", "error: ${error.message}", error)
        if (!isActive())
            return

        if (error is UserException) {
            Toast.makeText(activity, error.message, Toast.LENGTH_LONG)
        } else {
            Toast.makeText(activity, "Unknown error" + "\n" + error.message, Toast.LENGTH_LONG)
        }
    }

    private fun openFragment(openFragmentData: OpenFragmentData){
        if (!isActive())
            return

        val fragment = FragmentsFactory.newInstance(openFragmentData.fragmentType, openFragmentData.data)
        val fragTransaction = activity.supportFragmentManager.beginTransaction()
        fragTransaction.replace(R.id.container, fragment, null)
        if (openFragmentData.addToBackStack) {
            fragTransaction.addToBackStack(null)
        }
        fragTransaction.commit()
    }

}