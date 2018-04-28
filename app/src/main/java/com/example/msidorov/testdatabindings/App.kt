package com.example.msidorov.testdatabindings

import android.app.Application
import com.example.msidorov.testdatabindings.data.database.AppDatabase

/**
 * @author m.sidorov
 */
class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    var initialized: Boolean = false
        private set;

    override fun onCreate() {
        super.onCreate();

        instance = this;
        initialize();
    }

    fun reinitialize(){
        release()
        initialize()
    }

    fun release(){
        if (initialized) {
            AppDatabase.instance.close()
            initialized = false;
        }
    }

    private fun initialize(){
        AppDatabase.createInstance(this);
        initialized = true;
    }

}