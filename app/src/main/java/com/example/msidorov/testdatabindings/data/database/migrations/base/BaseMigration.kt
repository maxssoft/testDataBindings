package com.example.msidorov.testdatabindings.data.database.migrations.base

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import com.example.msidorov.testdatabindings.data.database.migrations.DatabaseMigrationError
import com.example.msidorov.testdatabindings.utils.LogLevel
import com.example.msidorov.testdatabindings.utils.isNull
import com.example.msidorov.testdatabindings.utils.log

/**
 * Абстрактный класс с реализацией кода, выполняющего обновление и фиксирующего его в логах

 * @author m.sidorov
 */
abstract class BaseMigration(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {

    val version: Int

    init {
        version = endVersion
    }

    // Выполняет миграцию базы данных и фиксирует ее в логах
    final override fun migrate(database: SupportSQLiteDatabase) {
        log(LogLevel.INFO, "migrate database to $version version")
        try {

            doMigrate(database)

            log(LogLevel.INFO, "success maigration to $version version")
        } catch (e: Exception) {
            val migrationError = DatabaseMigrationError("failed database migration to $version version with exception\n" + e.message.isNull(""), e)
            log(LogLevel.ERROR, migrationError.message, e)
            throw migrationError
        }
    }

    protected abstract fun doMigrate(database: SupportSQLiteDatabase)

}

