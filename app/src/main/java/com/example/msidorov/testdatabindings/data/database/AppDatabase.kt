package com.example.msidorov.testdatabindings.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.example.msidorov.testdatabindings.data.dao.UserContactDao
import com.example.msidorov.testdatabindings.data.dao.UserDao
import com.example.msidorov.testdatabindings.data.entity.UserContactEntity
import com.example.msidorov.testdatabindings.data.entity.UserEntity

/**
 * @author m.sidorov
 */
@Database(entities = arrayOf(UserEntity::class, UserContactEntity::class), version = MigrationManager.DATABE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userContactDao(): UserContactDao

    companion object {
        @JvmStatic
        lateinit var instance: AppDatabase
            private set

        @JvmStatic
        @Synchronized
        fun createInstance(context: Context) {

            val migrations = MigrationManager.registerMigrations(context)

            instance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase::class.java, "local.db")
                    .allowMainThreadQueries()
                    .addMigrations(*migrations)
                    //.fallbackToDestructiveMigration()
                    .build()
        }

    }
}
