package com.example.msidorov.testdatabindings

import android.app.Application
import com.example.msidorov.testdatabindings.data.database.AppDatabase
import com.example.msidorov.testdatabindings.domain.repository.RepositoryProvider

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

    lateinit var database: AppDatabase
        private set

    lateinit var repositories: RepositoryProvider
        private set

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
            database.close()
            initialized = false;
        }
    }

    private fun initialize(){
        AppDatabase.createInstance(this);

        database = AppDatabase.instance
        repositories = RepositoryProvider(database)

        initialized = true;
    }

}